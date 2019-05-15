package com.citrus.demo.citrustest;

import org.springframework.context.annotation.Bean;

import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EndpointConfig {
	
    @Bean
    public HttpClient oAuthClient() {
        return CitrusEndpoints
            .http()
                .client()
                .requestUrl("https://test-lmidp.libertymutual.com")
            .build();
    }
    
    @Bean
    public HttpClient locationServiceClient() {
        return CitrusEndpoints
            .http()
                .client()
                .requestUrl("https://nip-pw-location-code-service-stage.us-east-1.np.paas.lmig.com")
            .build();
    }

}
