package com.ecommerce.api_gateway;
import com.ecommerce.api_gateway.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    	System.out.println("I am here first");

        return builder.routes()
                .route("user_route", r -> r.path("/api/user/**")
                		.filters(f -> f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config())))
                        .uri("lb://user-service"))
                .build();
    }

    @Bean
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
            return chain.filter(exchange);
        };
    }
}