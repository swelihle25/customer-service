package com.khaya.customer_service.service;

import com.khaya.customer_service.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WebClient transactionWebClient;

    public List<TransactionResponse> getTransactions(String accountNumber, String status) {
        log.info("Calling payments service to get transactions");
        return transactionWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/payments/v1/accounts/{accountNumber}")
                        .queryParam("status", status)
                        .build(accountNumber))
                .retrieve()
                .bodyToFlux(TransactionResponse.class)
                .collectList()
                .timeout(java.time.Duration.ofSeconds(30))
                .block();
    }


}
