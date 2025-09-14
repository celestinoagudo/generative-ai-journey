package com.example.lab02_azure.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service("stockService")
public class StockService {
    public record Stock(Double price, Integer volume) {
    }

    private static final Map<String, Stock> stockTable;

    static {
        final var random = new Random();
        stockTable = Map.of(
                "NVDA", new Stock(Math.round(Math.random() * 800) / 100.0, (random.nextInt() * 999 + 1)),
                "TSLA", new Stock(Math.round(Math.random() * 700) / 100.0, (random.nextInt() * 999 + 1)),
                "AAPL", new Stock(Math.round(Math.random() * 600) / 100.0, (random.nextInt() * 999 + 1)),
                "AMZN", new Stock(Math.round(Math.random() * 500) / 100.0, (random.nextInt() * 999 + 1))
        );
    }

    public Integer getStockVolume(String symbol) {
        var stock = stockTable.get(symbol);
        if (Objects.isNull(stock)) {
            throw new IllegalArgumentException("Unknown stock symbol: " + symbol);
        }
        return stock.volume;
    }

    public Double getStockPrice(String symbol) {
        var stock = stockTable.get(symbol);
        if (Objects.isNull(stock)) {
            throw new IllegalArgumentException("Unknown stock symbol: " + symbol);
        }
        return stock.price;
    }

    public Double calculatePortfolioValue(List<StockTools.Request> stocks) {
        var totalValue = 0.0;
        for (var stock : stocks) {
            totalValue += stock.quantity() * stock.price();
        }
        return Math.round(totalValue * 100) / 100.0;
    }

    public String calculatePortfolioValueFormatted(List<StockTools.Request> stocks) {
        var totalValue = this.calculatePortfolioValue(stocks);
        return String.format("%,.2f", totalValue);
    }
}
