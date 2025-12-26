package com.khaya.customer_service.controller;

import com.khaya.customer_service.dto.TransactionResponse;
import com.khaya.customer_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts/{accountNumber}")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable String accountNumber,
                                                                     @RequestParam(required = false) String status,
                                                                     Authentication authentication) {

        log.info("Fetching transactions for accountNumber={}", accountNumber);

        return new ResponseEntity<>(transactionService.getTransactions(accountNumber, status), HttpStatus.OK);
    }
}
