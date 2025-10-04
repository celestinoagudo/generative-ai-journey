package com.example.sql_generator.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class AIClient {

    private final ChatModel chatModel;
    private final ResourceLoader resourceLoader;

    public AIClient(final OpenAiChatModel openAiChatModel, final ResourceLoader resourceLoader) {
        this.chatModel = openAiChatModel;
        this.resourceLoader = resourceLoader;
    }

    public String summarize(final String userMessage, final String supportingData) {
        var systemMessage =
                "You are a web service which specializes in executive summaries.";
        var fullUserMessage = userMessage + ".  Supporting data:  " + supportingData;

        var chatClient = ChatClient.builder(chatModel).defaultSystem(systemMessage).build();
        return chatClient.prompt().user(fullUserMessage).call().content();
    }

    public String generateSql(final String input) {
        var systemMessage =
                """
                        You are an SQL generating web service.
                        Responses must be valid, HyperSQL-compatible, executable SQL statements.  
                        HyperSQL uses DATE_ADD ( xxxx, INTERVAL X DAY ) for date arithmetic, and CURRENT_DATE to get today's date.
                        The SQL statement must be placed between <SQL> and </SQL> tags.
                        Do not include any other superflous text in the response.
                        Use the following database schema to generate SQL queries: %s
                        """;

        var schema = readSchemaFile();
        var fullSystemPrompt = String.format(systemMessage, schema);
        var chatClient = ChatClient.builder(chatModel).defaultSystem(fullSystemPrompt).build();
        var response = chatClient.prompt().user(input).call().content();
        return extractSql(response); // Replace this
    }

    private String readSchemaFile() {
        StringBuilder schema = new StringBuilder();
        try {
            Resource resource = resourceLoader.getResource("classpath:schema.sql");
            try (InputStream inputStream = resource.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    schema.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schema.toString();
    }

    private String extractSql(String response) {
        assert response.contains("<SQL>") && response.contains("</SQL>");

        // Extract the SQL statement from the response:
        return response.substring(
                response.indexOf("<SQL>") + "<SQL>".length(),
                response.indexOf("</SQL>"));
    }


}
