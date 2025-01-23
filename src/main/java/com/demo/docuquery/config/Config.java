package com.demo.docuquery.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Bean
    public EmbeddingModel embeddingModel(){
        return new OpenAiEmbeddingModel(new OpenAiApi(apiKey));
    }

    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel) {
        return new SimpleVectorStore(embeddingModel);
    }

    @Bean
    public QuestionAnswerAdvisor questionAnswerAdvisor(VectorStore vectorStore) {
        // Explicitly define the QuestionAnswerAdvisor bean to ensure the VectorStore bean is injected correctly
        return new QuestionAnswerAdvisor(vectorStore);
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder, QuestionAnswerAdvisor questionAnswerAdvisor) {
        // Use the QuestionAnswerAdvisor bean
        return builder.defaultAdvisors(questionAnswerAdvisor).build();
    }
}
