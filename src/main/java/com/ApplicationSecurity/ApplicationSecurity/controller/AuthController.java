package com.ApplicationSecurity.ApplicationSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApplicationSecurity.ApplicationSecurity.Dto.UserDTO;
import com.ApplicationSecurity.ApplicationSecurity.Service.AuthService;
import com.ApplicationSecurity.ApplicationSecurity.config.RateLimiterConfig;

import jakarta.validation.Valid;

/**
 * Controller for Registration and Login
 * @author akshataggarwal
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
	
    private final AuthService authService;
    
    @Autowired
    private RateLimiterConfig rateLimiter;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO) {
        authService.registerUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDTO userDTO) {
        if (!rateLimiter.allowRequest()) {
            return ResponseEntity.status(429).body("Too many requests. Try again later.");
        }
        String token = authService.authenticateUser(userDTO);
        return (token != null) ? ResponseEntity.ok(token) : ResponseEntity.status(401).body("Invalid credentials");
    }

}
