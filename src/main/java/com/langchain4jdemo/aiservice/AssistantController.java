package com.langchain4jdemo.aiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
class AssistantController {

    @Autowired
    Assistant assistant;

    @Autowired
    StreamingAssistant streamingAssistant;


    @GetMapping("/assistant")
    public String assistant(@RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
        return assistant.chat(message);
    }

    @GetMapping("/streamingAssistant")
    public Flux<String> streamingAssistant(@RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
        return streamingAssistant.chat(message);
    }
}
