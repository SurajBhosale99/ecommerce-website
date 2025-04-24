package com.ecommerce.api_gateway.security;

import com.ecommerce.common.security.JwtUtil;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
            	System.out.println("I am here");
                return chain.filter(exchange);
                
            }
            System.out.println("I am here In JwtAuthenticationFilter");

            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            System.out.println("Authorization Header: " + authHeader);

            
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                exchange.getResponse().getHeaders().add("WWW-Authenticate", "Bearer realm=\"Access to resource\"");
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);
            System.out.println("Token:" +token);
            Claims claims;
            try {
                //jwtUtil.validateToken(token); // throws if invalid
                claims = jwtUtil.validateToken(token);
                System.out.println("Role in JWT claims: " + claims.get("role"));


            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                exchange.getResponse().getHeaders().add("WWW-Authenticate", "Bearer error=\"invalid_token\"");
                return exchange.getResponse().setComplete();
            }
            
            // Modify the request to add headers
         // Add claims to headers (optional custom headers)
            
            System.out.println("Token" + token);

            
            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                    .header("Authorization", "Bearer " + token)
                    .header("X-User-Id", claims.getSubject()) // user ID
                    .header("X-User-Role", claims.get("role", String.class)) // role
                    .build();
            
            System.out.println("Second call");
            System.out.println("Second call" + modifiedRequest.getHeaders().AUTHORIZATION);


            return chain.filter(exchange.mutate().request(modifiedRequest).build());

        };
    }
}