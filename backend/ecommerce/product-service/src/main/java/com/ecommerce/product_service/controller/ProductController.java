package com.ecommerce.product_service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("/message")
    public String getMessage() {
        return "You are logged in Product Service through API Gateway";
    }
}
