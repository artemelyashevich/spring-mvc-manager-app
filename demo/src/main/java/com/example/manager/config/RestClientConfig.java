package com.example.manager.config;

import com.example.manager.client.ProductRestClient;
import com.example.manager.client.ProductRestClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public ProductRestClientImpl productRestClient(
            @Value("${manager.service.catalogue.uri:http://localhost:8081}") String baseUri
    ) {
        return new ProductRestClientImpl(RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build());
    }
}
