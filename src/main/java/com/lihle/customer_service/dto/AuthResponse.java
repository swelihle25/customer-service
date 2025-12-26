package com.lihle.customer_service.dto;

import com.lihle.customer_service.dao.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Long customerId;
    private String name;
    private List<Account> customerAccounts;
}
