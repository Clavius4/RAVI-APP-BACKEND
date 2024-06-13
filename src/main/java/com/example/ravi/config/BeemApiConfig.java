package com.example.ravi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class BeemApiConfig {

    @Value("${beem.api.username}")
    private String apiUsername;

    @Value("${beem.api.password}")
    private String apiPassword;

    private final RestTemplate restTemplate;

    public BeemApiConfig(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    public RestTemplate beemApiRestTemplate() {
        ClientHttpRequestInterceptor interceptor = new BasicAuthenticationInterceptor(apiUsername, apiPassword);
        restTemplate.setInterceptors(Collections.singletonList(interceptor));
        return restTemplate;
    }
}
