package com.example.lab02_azure.client;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class AIClientImpl {
    private final ChatClient chatClient;

    public AIClientImpl(final AzureOpenAiChatModel azureOpenAiChatModel) {
        this.chatClient = ChatClient.builder(azureOpenAiChatModel).build();
    }

    public String callApi(final String prompt) {
        return chatClient.prompt().user(prompt).call().content();
    }
}
