package com.khaya.customer_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaya.customer_service.dto.DisputeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisputeService {

    private final WebClient disputeWebClient;
    private final ObjectMapper objectMapper;

    public Map createDispute(String transactionReference, String customerId, String accountNumber, DisputeRequest req) {
        var payload = Map.of(
                "transactionReference", transactionReference,
                "customerId", customerId,
                "accountNumber", accountNumber,
                "reason", req.getReason()
        );
        log.info("c {}", payload);
        return disputeWebClient.post().uri("/disputes")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(java.time.Duration.ofSeconds(30))
                .block();

    }

    public List<Map<String, Object>> getDisputes(String customerId, String accountNumber) {
        return disputeWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/disputes/customers/{customerId}/accounts/{accountNumber}")
                        .build(customerId, accountNumber))
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Map<String, Object>>() {}) //WebClient parse JSON objects into Maps
                .timeout(Duration.ofSeconds(30))
                .collectList()
                .block();
    }



}
