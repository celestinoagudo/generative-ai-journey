package com.example.sql_generator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ProductServiceTest {

    private final String samplePrompt = "List the sales of the top 3 product names by revenue during the last 30 days.";
    private final String[] sampleResults = {"Smart Watch", "149.85", "Gaming Console", "121.50", "Digital SLR Camera", "120.00"};

    @Autowired
    private ProductService productService;

    @Test
    void productQueryTest() {
        var response = productService.productQuery(samplePrompt);
        System.out.printf("Response: %s", response);
        assertThat(response).isNotNull().contains(sampleResults);
    }
}