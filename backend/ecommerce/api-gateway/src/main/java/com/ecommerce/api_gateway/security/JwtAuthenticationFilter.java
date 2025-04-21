package com.ecommerce.api_gateway.security;

import com.ecommerce.common.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
        // Configuration class (can stay empty)
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getPath().toString();

            // Allow unauthenticated OTP generation
            if (path.equals("/api/user/generate")|| path.equals("/api/user/verify")) {
                return chain.filter(exchange);
            }

            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                exchange.getResponse().getHeaders().add("WWW-Authenticate", "Bearer realm=\"Access to resource\"");
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);

            try {
                jwtUtil.validateToken(token); // throws if invalid
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                exchange.getResponse().getHeaders().add("WWW-Authenticate", "Bearer error=\"invalid_token\"");
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        };
    }
}