package com.example.lab02_azure.client;

import com.example.lab02_azure.service.StockTools;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class AIClientImpl {
    private final ChatClient chatClient;
    private final StockTools tools;

    public AIClientImpl(final AzureOpenAiChatModel azureOpenAiChatModel, StockTools tools) {
        this.chatClient = ChatClient.builder(azureOpenAiChatModel).build();
        this.tools = tools;
    }

    public String callApi(final String prompt) {
        return chatClient.prompt().user(prompt).call().content();
    }

    public String callApiWithTools(String prompt) {
        return chatClient.prompt(prompt).tools(tools).call().content();
    }
}
