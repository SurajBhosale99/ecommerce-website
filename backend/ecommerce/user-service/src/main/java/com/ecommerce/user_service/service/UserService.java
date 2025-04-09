package com.ecommerce.user_service.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.common.Role;
import com.ecommerce.user_service.dao.UserRepository;
import com.ecommerce.user_service.entity.User;

@Service
public class UserService {

	//private final UserRepository userRepository;
    private final Map<String, String> otpStore = new HashMap<>();
    private final Map<String, Long> otpExpiry = new HashMap<>();
    private final long OTP_EXPIRY_TIME = 300000; // 5 minutes in milliseconds

    @Autowired
    private UserRepository userRepository;

    // Generate OTP and store it
    // Generate OTP method
    public String generateOTP(String mobileNumber) {
        // Generate a 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpStore.put(mobileNumber, otp);
        otpExpiry.put(mobileNumber, System.currentTimeMillis() + OTP_EXPIRY_TIME); // Expiry time: 5 minutes

        // Save or update user info in the database
        User user = userRepository.findByMobileNumber(mobileNumber)
                .orElse(new User(mobileNumber)); // If user not found, create a new one
        user.setOtpVerified(false); // Set OTP as unverified initially
        userRepository.save(user);
     // Set the default role if the user does not have a role already
        if (user.getRole() == null) {
            user.setRole(Role.USER); // Set default role as USER
        }
        // Simulate sending OTP to user's mobile number (replace with actual SMS integration)
       // sendOtpToMobile(mobileNumber, otp);

        return otp;
    }

    // Verify OTP
    public boolean verifyOTP(String mobileNumber, String otp) {
        String storedOtp = otpStore.get(mobileNumber);
        Long expiryTime = otpExpiry.get(mobileNumber);

        // Check if OTP is correct and not expired
        if (storedOtp != null && storedOtp.equals(otp) && System.currentTimeMillis() < expiryTime) {
            otpStore.remove(mobileNumber); // Remove OTP after successful verification
            otpExpiry.remove(mobileNumber);

            // Update user's OTP status in the database
            User user = userRepository.findByMobileNumber(mobileNumber)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            user.setOtpVerified(true);
            userRepository.save(user); // Save the updated OTP verification status

            return true;
        }
        return false;
    }
}