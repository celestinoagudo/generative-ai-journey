package com.example.lab02_azure.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * These are the specific 'tools' made available to the model.
 * Spring AI will make the model aware that the Client has tools that it can use when it doesn't know the answer. The model will know the names of the tools,
 * * their purpose, what parameters they need, and what they produce.
 * When the model is attempting to solve a problem requiring the use of a tool, it will send a response back to the client, asking it to invoke the tool on its behalf.
 * - The Client understands the response is not the final response, but a request to call the tool to provide more information.
 * - The client calls the tool method and obtains a result.
 * - The client then sends another prompt to the model with the result from the tool.
 * - The model and client repeat this process as needed until the model understands it can produce a final response.
 * - The final response is returned to the caller. The caller does NOT observe the intermediate prompts and responses between the model and the Client.
 * Notice that some of the tool methods have complex inputs or outputs.
 */
public class StockTools {

    private final StockService service;

    public StockTools(StockService service) {
        this.service = service;
    }

    public record Request(Integer quantity, Double price) {
    }

    public record Response(String symbol, Double price, Integer volume, String currency) {
    }

    @Tool(description = "Returns current details for a given stock ticker symbol")
    public Response getStockInfo(String symbol) {
        return new Response(symbol, service.getStockPrice(symbol), service.getStockVolume(symbol), "USD");
    }

    @Tool(description = "Calculates the total value of a portfolio of stocks")
    public Double calculatePortfolioValue(List<Request> stocks) {
        return service.calculatePortfolioValue(stocks);
    }
}
