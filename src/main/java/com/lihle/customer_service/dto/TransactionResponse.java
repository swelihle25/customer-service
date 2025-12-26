package com.lihle.customer_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    String sourceAccountNumber;
    String destinationAccountNumber;
    String sourceBankName;
    String destinationBankName;
    String status;
    String beneficiaryName;
    BigDecimal amount;
    String currency;
    String transactionType;
    String description;
    String transactionReference;
    LocalDateTime requestedAt;
    String initiatedBy;
}
