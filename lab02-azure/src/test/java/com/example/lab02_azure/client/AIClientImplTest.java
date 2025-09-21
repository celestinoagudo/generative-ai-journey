package com.example.lab02_azure.client;

import com.example.lab02_azure.service.StockService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("azure")
class AIClientImplTest {

    @Autowired
    AIClientImpl client;

    @Autowired
    StockService stockService;

    @BeforeEach
    void setUp() {
        sampleResults = new String[]{"NVDA", stockService.getStockPrice("NVDA") + ""};
    }

    private String samplePrompt = "Provide a 50-100 word overview of company NVDA, including today's trading information such as price and volume.";
    String[] sampleResults;

    @Test
    void callApiTest() {
        var response = client.callApi("Can you name five great lakes?");
        Assertions.assertThat(response).isNotBlank();
        System.out.println("The results of the call are: " + response);
    }

    @Test
    void callApiTestWithTools() {
        var response = client.callApiWithTools(samplePrompt);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).contains(sampleResults);
        System.out.println("The results of the call are: " + response);
    }
}