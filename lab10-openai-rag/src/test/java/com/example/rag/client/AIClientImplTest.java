package com.example.rag.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AIClientImplTest {

    @Autowired
    AIClient aiClient;

    @Test
    void testGetProductRecommendations() {
        aiClient.save(Utilities.productCatalog);
        var response = aiClient.getProductRecommendations(Utilities.query);
        System.out.println(response);
        Assertions.assertThat(response).isNotNull();
    }
}