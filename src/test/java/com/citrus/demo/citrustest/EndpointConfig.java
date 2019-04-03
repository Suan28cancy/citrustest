package com.citrus.demo.citrustest;

import org.springframework.context.annotation.Bean;

import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EndpointConfig {
	
    @Bean
    public HttpClient journelClient() {
        return CitrusEndpoints
            .http()
                .client()
                .requestUrl("https://ci-wc-journal-service-dev.pdc.np.paas.lmig.com")
            .build();
    }

}
