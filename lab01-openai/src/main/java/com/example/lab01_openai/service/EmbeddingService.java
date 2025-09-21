package com.example.lab01_openai.service;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmbeddingService {

    private final EmbeddingModel model;

    public EmbeddingService(final EmbeddingModel model) {
        this.model = model;
    }

    public String findClosestMatch(String query, List<String> products) {
        List<float[]> productEmbeddings = null;
        productEmbeddings = model.embed(products);

        float[] queryEmbedding = null;
        queryEmbedding = model.embed(query);

        int mostSimilarIndex = -1;
        mostSimilarIndex = findClosestMatch(queryEmbedding, productEmbeddings);

        if (mostSimilarIndex < 0) {
            return "No similar product found";
        }
        return products.get(mostSimilarIndex);
    }

    public static int findClosestMatch(float[] query, List<float[]> products) {
        int mostSimilarIndex = -1;
        double mostSimilarScore = -1;
        for (var i = 0; i < products.size(); ++i) {
            float[] productEmbedding = products.get(i);
            double similarity = cosineSimilarity(query, productEmbedding);
            if (similarity > mostSimilarScore) {
                mostSimilarScore = similarity;
                mostSimilarIndex = i;
            }
        }
        return mostSimilarIndex;
    }

    //  Calculate the cosine similarity between two vectors:
    //  This is a way to measure the similarity between two vectors of numbers.
    public static double cosineSimilarity(float[] vectorA, float[] vectorB) {
        if (vectorA.length != vectorB.length) {
            throw new IllegalArgumentException("Vectors must have the same length");
        }

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vectorA.length; ++i) {
            double a = vectorA[i];
            double b = vectorB[i];
            dotProduct += a * b;
            normA += a * a;
            normB += b * b;
        }
        //Handle cases where either norm is zero (to avoid division by zero)
        if (normA == 0 || normB == 0) {
            return 0.0;
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
