package com.example.sql_generator.service;

import com.example.sql_generator.client.AIClient;
import com.example.sql_generator.dao.ProductDao;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final AIClient aiClient;
    private final ProductDao productDao;

    public ProductService(final AIClient aiClient, final ProductDao productDao) {
        this.aiClient = aiClient;
        this.productDao = productDao;
    }

    public String productQuery(final String userQuery) {
        var sql = aiClient.generateSql(userQuery);
        var results = productDao.adHocQuery(sql);
        return aiClient.summarize(userQuery, results.toString());
    }
}
