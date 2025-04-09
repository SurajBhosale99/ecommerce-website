package com.ecommerce.user_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user_service.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/generate")
    public String generateOTP(@RequestBody Map<String, String> requestBody) {
        String mobileNumber = requestBody.get("mobile"); // Get "mobile" from the request
        return userService.generateOTP(mobileNumber);
    }
    @PostMapping("/verify")
    public String verifyOTP(@RequestParam String mobileNumber, @RequestParam String otp) {
        return userService.verifyOTP(mobileNumber, otp) ? "OTP Verified!" : "Invalid OTP or OTP expired.";
    }
}
