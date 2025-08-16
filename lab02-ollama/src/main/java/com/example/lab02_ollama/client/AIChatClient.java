package com.example.lab02_ollama.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Component;

@Component
public class AIChatClient {

    private final ChatClient chatClient;

    public AIChatClient(final OllamaChatModel ollamaChatModel) {
        this.chatClient = ChatClient.builder(ollamaChatModel).build();
    }

    public String callApi(final String prompt) {
        return chatClient.prompt().user(prompt).call().content();
    }
}
