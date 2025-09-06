package com.example.lab05_openai_image.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OpenAIClientTest {
    @Autowired
    AIClient client;

    @Test
    void testCreateImageUrl() {
        var url = client.createImageUrl("Two golden retrievers playing tug-o-war in the snow.");
        assertThat(url).isNotBlank();
        System.out.println("URL: " + url);
    }

    @Test
    void testCreateImageBase64() {
        var base64 = client.createImageB64("Two golden retrievers playing tug-o-war in the snow.");
        assertThat(base64).isNotBlank();
        System.out.println("BASE 64: " + base64);
    }
}