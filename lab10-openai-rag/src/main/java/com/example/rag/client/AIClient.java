package com.example.rag.client;

import java.util.List;

public interface AIClient {
    void save(List<String> products);

    String getProductRecommendations(String query);
}
