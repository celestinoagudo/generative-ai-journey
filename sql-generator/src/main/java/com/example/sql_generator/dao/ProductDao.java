package com.example.sql_generator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {

    private final JdbcTemplate template;

    public ProductDao(final JdbcTemplate template) {
        this.template = template;
    }

    public List<Map<String, Object>> adHocQuery(final String sql) {
        return template.queryForList(sql);
    }
}
