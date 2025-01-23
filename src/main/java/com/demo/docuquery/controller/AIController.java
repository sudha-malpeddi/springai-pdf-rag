package com.demo.docuquery.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {
    private ChatClient chatClient;

    public AIController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping()
    Map<String, String> completion(@RequestParam(value = "msg", defaultValue = "Tell me a joke") String message) {
        return Map.of(
                "completion",
                chatClient.prompt()
                        .user(message)
                        .call()
                        .content());
    }

    @GetMapping("/sports")
    public String getPopularSportsPerson(@RequestParam(value = "sport", defaultValue = "Cricket") String sport){
        PromptTemplate template = new PromptTemplate("List top 5 famous personalities of {sport}");
        Prompt prompt = template.create(Map.of("sport", sport));
        return chatClient.prompt(prompt).call().content();
    }
}
