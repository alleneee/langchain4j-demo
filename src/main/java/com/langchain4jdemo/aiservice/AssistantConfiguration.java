package com.langchain4jdemo.aiservice;

import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AssistantConfiguration {


    @Bean
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    TextDocumentParser textDocumentParser() {
        return new TextDocumentParser();
    }

    @Bean
    OpenAiTokenizer tokenizer() {
        return new OpenAiTokenizer();
    }
}
