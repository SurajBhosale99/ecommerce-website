package com.ecommerce.user_service.service;

import com.ecommerce.common.Role;
import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.security.JwtUtil;
import com.ecommerce.user_service.dao.UserRepository;
import com.ecommerce.user_service.entity.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class UserService {

    private final Map<String, String> otpStore = new HashMap<>();
    private final Map<String, Long> otpExpiry = new HashMap<>();
    private final long OTP_EXPIRY_TIME = 300000; // 5 mins
    private static final Logger logger = LogManager.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String generateOTP(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.trim().isEmpty()) {
            throw new CustomException(
                "Mobile number cannot be empty.",
                "EMPTY_MOBILE_NUMBER",
                "Please provide a valid 10-digit mobile number."
            );
        }

        if (mobileNumber.length() != 10 || !mobileNumber.matches("\\d{10}")) {
            throw new CustomException(
                "Mobile number should be exactly 10 digits.",
                "INVALID_MOBILE_NUMBER",
                "Please enter a valid 10-digit mobile number containing only numbers."
            );
        }

        try {
            String otp = String.format("%06d", new Random().nextInt(999999));
            otpStore.put(mobileNumber, otp);
            otpExpiry.put(mobileNumber, System.currentTimeMillis() + OTP_EXPIRY_TIME);

            User user = userRepository.findByMobileNumber(mobileNumber)
                    .orElse(new User(mobileNumber));
            user.setOtpVerified(false);
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);

            logger.info("Generating OTP for mobile number: {}", mobileNumber);
            logger.debug("Generated OTP: {}", otp);

            return otp;
        } catch (Exception ex) {
            logger.error("Error while generating OTP", ex);
            throw new CustomException(
                "Internal server error while generating OTP.",
                "OTP_GENERATION_FAILED",
                "Please try again later."
            );
        }
    }

    public String verifyOTP(String mobileNumber, String otp) {
        String storedOtp = otpStore.get(mobileNumber);
        Long expiry = otpExpiry.get(mobileNumber);

        if (storedOtp != null && storedOtp.equals(otp) && System.currentTimeMillis() < expiry) {
            otpStore.remove(mobileNumber);
            otpExpiry.remove(mobileNumber);

            User user = userRepository.findByMobileNumber(mobileNumber)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            user.setOtpVerified(true);
            userRepository.save(user);

            return jwtUtil.generateToken(user.getMobileNumber(), user.getRole());
        }

        throw new IllegalArgumentException("Invalid or expired OTP");
    }
}