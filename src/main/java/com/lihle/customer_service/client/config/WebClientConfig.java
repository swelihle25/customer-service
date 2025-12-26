package com.lihle.customer_service.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${transaction-service.base-url}")
    private String transactionBaseUrl;

    @Value("${dispute-service.base-url}")
    private String disputeBaseUrl;

    @Bean
    public WebClient transactionWebClient() {
        return WebClient.builder().baseUrl(transactionBaseUrl).build();
    }

    @Bean
    public WebClient disputeWebClient() {
        return WebClient.builder().baseUrl(disputeBaseUrl).build();
    }

}
