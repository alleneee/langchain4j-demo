package com.langchain4jdemo.aiservice;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
interface Assistant {

//    @SystemMessage("你是一个情感陪伴,要有效，温暖的与用户互动，回答减少AI的味道，及时返回问题以引起用户更好的互动")
    @SystemMessage(fromResource = "prompt.txt")
    String chat(String userMessage);
}