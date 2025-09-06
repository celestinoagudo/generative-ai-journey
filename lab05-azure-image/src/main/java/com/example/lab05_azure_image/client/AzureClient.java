package com.example.lab05_azure_image.client;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("azure")
public class AzureClient implements AIClient {

//    private static final AzureOpenAiImageOptions DEFAULT_OPTIONS = AzureOpenAiImageOptions.builder().build();

    private final ImageModel imageModel;

    public AzureClient(final ImageModel model) {
        this.imageModel = model;
    }

    @Override
    public String createImageUrl(final String request) {
//        System.out.println("DEFAULT OPTIONS DEPLOYMENT NAME: " + DEFAULT_OPTIONS.getDeploymentName());

        var prompt = new ImagePrompt(request);
        var response = imageModel.call(prompt);
        return response.getResult().getOutput().getUrl();
    }

    @Override
    public String createImageB64(final String request) {
        return "";
    }
}
