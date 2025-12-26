package com.lihle.customer_service.service;

import com.lihle.customer_service.config.JwtUtil;
import com.lihle.customer_service.dao.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerAuthService {

    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public Optional<String> authenticate(String email, String password) {
        return customerRepository.findByEmail(email)
                .filter(c -> c.getPassword().equals(password))
                .map(c -> jwtUtil.generateToken(c.getId().toString(), c.getEmail()));
    }
}