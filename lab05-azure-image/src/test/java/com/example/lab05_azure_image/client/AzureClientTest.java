package com.example.lab05_azure_image.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("azure")
class AzureClientTest {

    @Autowired
    AIClient aiClient;

    @Test
    void testCreateImageUrl() {
        var url = aiClient.createImageUrl("A hand-glider soaring over Yosemite valley on a bright summer day.");
        Assertions.assertThat(url).isNotBlank();
        System.out.println("URL: " + url);
    }

}