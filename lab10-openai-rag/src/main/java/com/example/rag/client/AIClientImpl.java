package com.example.rag.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIClientImpl implements AIClient {

    private static String DEFAULT_SYSTEM_MESSAGE =
            """
                    You are a helpful product expert that provides product recommendations to customers.
                    Each product recommendation should be brief, but MUST include 1) a summary of the product 2) the URL for more details.
                    """;
    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final RequestResponseAdvisor requestResponseAdvisor;

    /**
     * * Spring AI's autoconfiguration will have created the ChatModel and ChatClient.builder beans
     * automatically based on the dependency defined earlier.
     * * defaultSystem() defines the "system message" which defines the overall context, purpose, and rules to be followed by the Foundational Model.
     * It will be sent with every request made by this client.
     * * The system message tells the Foundational Model that it is a product expert that makes recommendations to customers.
     * Notice that it emphasizes that recommendations must include a summary and a URL; this will be important later when we test.
     *
     * @param chatModel   - Chat Model
     * @param vectorStore - Vector Store Implementation
     */
    public AIClientImpl(final OpenAiChatModel chatModel, final VectorStore vectorStore, final RequestResponseAdvisor requestResponseAdvisor) {
        this.chatClient = ChatClient.builder(chatModel).defaultSystem(DEFAULT_SYSTEM_MESSAGE).build();
        this.vectorStore = vectorStore;
        this.requestResponseAdvisor = requestResponseAdvisor;
    }

    @Override
    public void save(final List<String> products) {
        vectorStore.add(products.stream().map(Document::new).toList());
    }

    @Override
    public String getProductRecommendations(final String query) {
        return chatClient.prompt().user(query).advisors(requestResponseAdvisor).call().content();
    }
}
