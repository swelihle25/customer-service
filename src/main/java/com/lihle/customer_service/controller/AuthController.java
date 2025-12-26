package com.lihle.customer_service.controller;

import com.lihle.customer_service.dao.entity.Customer;
import com.lihle.customer_service.dao.repository.CustomerRepository;
import com.lihle.customer_service.dto.AuthRequest;
import com.lihle.customer_service.dto.AuthResponse;
import com.lihle.customer_service.service.CustomerAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerAuthService authService;
    private final CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return authService.authenticate(request.getEmail(), request.getPassword())
                .<ResponseEntity<?>>map(token -> {
                    Customer customer = customerRepository.findByEmail(request.getEmail()).get();
                    return ResponseEntity.ok(new AuthResponse(token, customer.getId(), customer.getName(), customer.getAccounts()));
                })
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
    }

}
