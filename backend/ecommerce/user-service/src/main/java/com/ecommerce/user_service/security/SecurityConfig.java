package com.ecommerce.user_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/user/generate", "/api/user/verify").permitAll()
                .requestMatchers("/api/user/admin").hasRole("ADMIN")
                .requestMatchers("/api/user/user").hasRole("USER")
                .anyRequest().authenticated()
            ).addFilterBefore(new CustomHeaderAuth(), UsernamePasswordAuthenticationFilter.class)
            .httpBasic();  // Or disable this too, if not needed

        return http.build();
    }
}