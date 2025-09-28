package com.example.lab01_openai.dao;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private final VectorStore vectorStore;

    public ProductDaoImpl(final VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void add(final List<String> products) {
        var documents = products.stream().map(Document::new).toList();
        vectorStore.add(documents);
    }

    @Override
    public List<String> findClosestMatches(final String query, final int numberOfMatches) {
        var searchRequest = SearchRequest.builder().query(query).topK(numberOfMatches).build();
        var results = vectorStore.similaritySearch(searchRequest);
        return results.stream().map(Document::getText).toList();
    }

    @Override
    public String findClosestMatch(final String query) {
        return findClosestMatches(query, 1).getFirst();
    }
}
