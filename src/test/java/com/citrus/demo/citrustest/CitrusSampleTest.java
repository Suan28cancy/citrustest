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
	HttpClient journelClient;
	
	@Test
	@CitrusTest
	public void test() {
        http(httpActionBuilder -> httpActionBuilder
                .client(journelClient)
                .send()
                .post("/journal/journals/")
                .contentType("application/json")
                .header("Authorization", "Basic citrus:encodeBase64('N0217055:Drum2570')")
                .header("application-uuid", "1234")
                .header("request-uuid", "5678")
                .payload("[{\"claimIdentifier\":\"as\",\"claimNumber\":234212345,\"createDate\": \"02/13/2012 07:15:32\",\"topicCode\": \"04\",\"titleName\": \"Medical - Vital Point\",\"externalCreateName\": \"LANGER, JUSTIN\",\"externalProcessName\": \"VitalPnt\",   \"externalUserIdentificationNumber\": \"n9999999\",\"text\": \"Fill Decision:Block Fill \\r\\nMedication Decisions:Allow Until 01/02/2011 \\r\\nMedication Restrictions:Generic Only \\r\\nPhysician Decisions:Block Until 01/02/2011 \\r\\nDo Not Send Auth:YES \\r\\nDecision Driver:Claim Rep Review \\r\\nDecision Reason:Allow Based on Review \\r\\nDecision Rendered By:Claim Representative \\r\\nDecision Rendered Behalf:SMITH, JOHN \\r\\nDecision By:LANGER, JUSTIN \\r\\nDecision Date Time:02/13/2012 07:15:32 \\r\\nComments:This is test message \\r\\nRx Date:11/2/2018 \\r\\nMedication Name:Xtampza ER \\r\\nPhysician Name:Johnson, Brad \\r\\nPhysician Phone:8085231414 \\r\\nPharmacy Name:Wal-mart store #4532 \\r\\nPharmacy Address:1234 Wilson St., Baltimore, MD, 44526-9822 \\r\\nPharmacy Phone:4563012255 \\r\\nRx#:346778 \\r\\nQuantity:30 \\r\\nDay Supply:15 \\r\\nTherapeutic Class:ANALGESICS - OPIOID \\r\\nBrand:Brand \\r\\nFill:1 of 3 \\r\\nCost:324.86 \\r\\n\"}]"));

            http(httpActionBuilder -> httpActionBuilder
                .client(journelClient)
                .receive()
                .response(HttpStatus.CREATED));
	}

}
