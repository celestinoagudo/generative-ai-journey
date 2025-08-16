package com.example.lab02_azure.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("azure")
class AIClientImplTest {

    @Autowired
    AIClientImpl client;

    @Test
    void callApiTest() {
        var response = client.callApi("I am going to Paris, what should I see?");
        Assertions.assertThat(response).isNotBlank();
        System.out.println("The results of the call are: " + response);
    }
}