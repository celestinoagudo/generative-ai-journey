package com.example.lab01_openai.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("openai")
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void testFindClosestMatch() {
        productService.save(Utilities.PRODUCTS);
        var result = productService.findClosestMatch(Utilities.SAMPLE_PROMPT);
        Assertions.assertThat(result).startsWith("Wireless Headphones:");
        System.out.println(result);
    }

}