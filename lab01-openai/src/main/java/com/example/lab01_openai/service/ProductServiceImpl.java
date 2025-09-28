package com.example.lab01_openai.service;

import com.example.lab01_openai.dao.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void save(final List<String> products) {
        productDao.add(products);
    }

    @Override
    public List<String> findClosestMatches(final String query) {
        return productDao.findClosestMatches(query, 5);
    }

    @Override
    public String findClosestMatch(final String query) {
        return productDao.findClosestMatch(query);
    }
}
