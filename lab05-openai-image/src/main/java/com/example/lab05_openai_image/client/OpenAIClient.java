package com.example.lab05_openai_image.client;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Component;

@Component("openAIClientBean")
public class OpenAIClient implements AIClient {

    private static final OpenAiImageOptions DEFAULT_OPTIONS = OpenAiImageOptions.builder().N(1).build();
    private final ImageModel imageModel;

    public OpenAIClient(final ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @Override
    public String createImageUrl(final String request) {
        var prompt = new ImagePrompt(request, DEFAULT_OPTIONS);
        var response = imageModel.call(prompt);
        return response.getResult().getOutput().getUrl();
    }

    @Override
    public String createImageB64(final String request) {
        var BASE64_OPTIONS =
                OpenAiImageOptions.builder()
                        .responseFormat("b64_json").build();
        var prompt = new ImagePrompt(request, BASE64_OPTIONS);
        var response = imageModel.call(prompt);
        return response.getResult().getOutput().getB64Json();
    }
}
