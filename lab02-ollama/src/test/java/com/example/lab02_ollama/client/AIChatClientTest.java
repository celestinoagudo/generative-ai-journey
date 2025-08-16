package com.example.lab02_ollama.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("ollama")
class AIChatClientTest {

    @Autowired
    public AIChatClient aiChatClient;

    @Test
    void callApiTest() {
        var response = aiChatClient.callApi("Can you name the five great lakes?");
        Assertions.assertThat(response).isNotBlank();
        System.out.println("The results of the call are: " + response);
    }
}