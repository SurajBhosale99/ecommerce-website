package com.ecommerce.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.user_service.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Operation(
            summary = "Generate OTP for User Verification",
            description = "Generates a one-time password (OTP) and sends it to the user's email or phone for verification."
        )
    // Generate OTP
    @PostMapping("/generate")
    public String generateOtp(@RequestParam String mobileNumber) {
        return userService.generateOTP(mobileNumber);
    }
    @Operation(
            summary = "Verify User OTP",
            description = "Verifies the one-time password (OTP) provided by the user to complete registration or login."
        )
    // Verify OTP and generate JWT token
    @PostMapping("/verify")
    public String verifyOtpAndGenerateToken(@RequestParam String mobileNumber, @RequestParam String otp) {
        System.out.println("Generating OTP for: " + mobileNumber);  // Log to see if it's reached

        return userService.verifyOTP(mobileNumber, otp); // Returns JWT token
    }
    
    @Operation(
            summary = "Admin-only endpoint",
            description = "Accessible only to users with ADMIN role. Used for admin-specific operations."
        )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String getAdminData() {
        return "Hello Admin! You have access to this data.";
    }
    

    @Operation(
        summary = "User-only endpoint",
        description = "Accessible only to users with USER role. Used for user-specific operations."
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String getUserData() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + auth.getAuthorities());

        return "Hello User! You have access to this data.";
    }
}