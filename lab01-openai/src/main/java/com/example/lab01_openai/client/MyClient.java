package com.example.lab01_openai.client;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("openai")
public class MyClient {

    private final OpenAiChatModel chatModel;

    public MyClient(final OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String call(final String input) {
        return chatModel.call(input);
    }

}
