package com.ecommerce.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	    "com.ecommerce.user_service",
	    "com.ecommerce.common.security"  // 👈 Add this line to include JwtUtil
	})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
 