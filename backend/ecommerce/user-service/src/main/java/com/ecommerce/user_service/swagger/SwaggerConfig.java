package com.ecommerce.user_service.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("User Service API")
                .version("1.0.0")
                .description("Handles user registration and login.")
                .contact(new Contact()
                    .name("Suraj Bhosale")
                    .email("surajsbhosale99@gmail.com")
                    .url("https://example.com")
                ));
    }
}
