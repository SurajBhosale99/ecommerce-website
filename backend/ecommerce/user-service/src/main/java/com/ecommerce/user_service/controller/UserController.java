package com.ecommerce.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.user_service.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Generate OTP
    @PostMapping("/generate")
    public String generateOtp(@RequestParam String mobileNumber) {
        return userService.generateOTP(mobileNumber);
    }

    // Verify OTP and generate JWT token
    @PostMapping("/verify")
    public String verifyOtpAndGenerateToken(@RequestParam String mobileNumber, @RequestParam String otp) {
        System.out.println("Generating OTP for: " + mobileNumber);  // Log to see if it's reached

        return userService.verifyOTP(mobileNumber, otp); // Returns JWT token
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String getAdminData() {
        return "Hello Admin! You have access to this data.";
    }

    //@PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String getUserData() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + auth.getAuthorities());

        return "Hello User! You have access to this data.";
    }
}