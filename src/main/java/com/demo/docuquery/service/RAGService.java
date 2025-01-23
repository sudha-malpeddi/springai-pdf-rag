package com.demo.docuquery.service;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class RAGService {
    private VectorStore vectorStore;

    public RAGService(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }


}
