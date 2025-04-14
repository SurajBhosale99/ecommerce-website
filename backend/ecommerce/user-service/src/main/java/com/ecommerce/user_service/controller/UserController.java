package com.ecommerce.user_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.user_service.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/generate")
    public String generateOTP(@RequestBody Map<String, String> requestBody) {
        String mobileNumber = requestBody.get("mobile");
        return userService.generateOTP(mobileNumber);
    }

    // New Endpoint: Authenticates via OTP and returns JWT
    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> requestBody) {
        String mobileNumber = requestBody.get("mobile");
        String otp = requestBody.get("otp");

        String token = userService.verifyAndGenerateToken(mobileNumber, otp);

        if (token == null) {
            return ResponseEntity.status(401).body("Invalid or expired OTP.");
        }

        return ResponseEntity.ok(Map.of("token", token));
    }

}