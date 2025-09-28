package com.example.lab01_openai;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Lab01OpenaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab01OpenaiApplication.class, args);
    }

    @Bean
    @Profile("openai")
    public VectorStore vectorStore(final EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }

}
