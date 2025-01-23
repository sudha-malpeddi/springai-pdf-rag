package com.demo.docuquery.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.document.Document;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RAGController {
    private final ChatClient aiClient;
    private final VectorStore vectorStore;

    public RAGController(ChatClient aiClient, VectorStore vectorStore) {
        this.aiClient = aiClient;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/rag")
    public ResponseEntity<String> generateAnswer(@RequestParam String query) {
        List<Document> similarDocuments = vectorStore.similaritySearch(query);

        if (similarDocuments.isEmpty()) {
            return ResponseEntity.ok("Unknown");
        }

        String information = similarDocuments.stream()
                .map(Document::getContent)
                .collect(Collectors.joining(System.lineSeparator()));

        // If no relevant documents are found, return "Unknown"
        if (information.trim().isEmpty()) {
            return ResponseEntity.ok("Unknown");
        }


        var systemPromptTemplate = new SystemPromptTemplate(
                """
                              
                                       
                                        {information}
                        """);
        var systemMessage = systemPromptTemplate.createMessage(Map.of("information", information));
        var userPromptTemplate = new PromptTemplate("{query}");
        var userMessage = userPromptTemplate.createMessage(Map.of("query", query));
        var prompt = new Prompt(List.of(userMessage));
        return ResponseEntity.ok(aiClient.prompt(prompt).call().content());
    }
}
