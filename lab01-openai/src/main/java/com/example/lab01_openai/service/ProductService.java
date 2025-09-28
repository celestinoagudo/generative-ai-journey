package com.example.lab01_openai.service;

import java.util.List;

public interface ProductService {
    void save(List<String> products);

    List<String> findClosestMatches(String query);

    String findClosestMatch(String query);
}
