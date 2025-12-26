package com.lihle.customer_service.controller;

import com.lihle.customer_service.dto.DisputeRequest;
import com.lihle.customer_service.service.DisputeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers/{customerId}/accounts/{accountNumber}")
@RequiredArgsConstructor
public class DisputeController {

    private final DisputeService disputeService;

    @PostMapping("/transactions/{transactionId}/disputes")
    public ResponseEntity<Map> createDispute(
            @PathVariable String customerId,
            @PathVariable String accountNumber,
            @PathVariable String transactionId,
            @RequestBody DisputeRequest req,
            Authentication authentication) {

        log.info("Creating dispute for customerId={} accountNumber={} transctionId={}",
                customerId, accountNumber, transactionId);

        var response = disputeService.createDispute(transactionId, customerId, accountNumber, req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/disputes")
    public ResponseEntity<List<Map<String, Object>>> getDisputes(
            @PathVariable String customerId,
            @PathVariable String accountNumber,
            Authentication authentication) {

        log.info("Fetching disputes for customerId={} accountNumber={}", customerId, accountNumber);

        var disputes = disputeService.getDisputes(customerId, accountNumber);

        return ResponseEntity.ok(disputes);
    }
}
