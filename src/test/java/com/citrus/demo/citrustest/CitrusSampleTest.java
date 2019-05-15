package com.citrus.demo.citrustest;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.springframework.http.HttpStatus;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;

public class CitrusSampleTest extends TestNGCitrusTestRunner {
	
	@Autowired
	HttpClient oAuthClient;
	
	@Autowired
	HttpClient locationServiceClient;
	
	@Test
	@CitrusTest
	public void test() {
        http(httpActionBuilder -> httpActionBuilder
                .client(oAuthClient)
                .send()
                .post("/as/token.oauth2")
                .contentType("application/x-www-form-urlencoded")
                .header("Authorization", "Basic citrus:encodeBase64('ci_niplcmsclient_4:955be1ac-d3af-4c86-b8cb-e4f69bbafc5d')")
                .payload("grant_type=client_credentials&scope:CI_ATWORK_COM"));

            http(httpActionBuilder -> httpActionBuilder
                .client(oAuthClient)
                .receive()
                .response(HttpStatus.OK)
                .payload("{\"access_token\":\"@variable('accessToken')@\",\"token_type\":\"@variable('tokenType')@\",\"expires_in\":\"@variable('expiresIn')@\"}"));
            
            
            echo("Access token is: ${accessToken}");
            echo("Token Type is: ${tokenType}");
            echo("Expires in ${expiresIn}");
            
            http(httpActionBuilder -> httpActionBuilder
                    .client(locationServiceClient)
                    .send()
                    .post("/services/niplcs/cope/update-cope-data")
                    .contentType("application/json")
                    .header("Authorization", "${tokenType} ${accessToken}")
                    .payload("{\"agreementId\":0,\r\n" + "\"subLocId\":14036975,\r\n" + "\"copyPolicyIndicator\":\"N\",\r\n"
    						+ "\"userId\":2280}  \r\n" + ""));

                http(httpActionBuilder -> httpActionBuilder
                    .client(locationServiceClient)
                    .receive()
                    .response(HttpStatus.CREATED)
                    );
	}

}
