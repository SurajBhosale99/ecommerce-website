
package com.ecommerce.api_gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("admin_route", r -> r.path("/api/admin/**")
                        .filters(f -> f.filter(customFilter())) // Apply filter
                        .uri("lb://admin-service"))
                .route("payment-service", r -> r.path("/api/payment/**")
                        .uri("lb://payment-service")) // Eureka LB lookup for payment-service
                .route("product-service", r -> r.path("/api/product/**")
                        .uri("lb://product-service")) // Eureka LB lookup for product-service
                .route("user-service", r -> r.path("/api/user/**")
                        .uri("lb://user-service"))
                .build();
    }

    // Custom Filter Bean (Logging or other processing)
    @Bean
    public GatewayFilter customFilter() {
        return (exchange, chain) -> {
            System.out.println("Custom filter executed: " + exchange.getRequest().getPath());
            return chain.filter(exchange);
        };
    }

    // Global Filter (applies to all requests)
    @Bean
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            // Add CORS headers to response
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Credentials", "true");

            // Handle pre-flight OPTIONS requests
            if (exchange.getRequest().getMethod().name().equals("OPTIONS")) {
                return exchange.getResponse().setComplete(); // Pre-flight request: just return a 200 response
            }

            // Continue with the filter chain
            return chain.filter(exchange).then(Mono.fromRunnable(() ->
                    System.out.println("Post-processing response...")));
        };
    }
}