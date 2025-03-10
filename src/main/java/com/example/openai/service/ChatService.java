package com.example.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;
    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message) {
        return chatClient.prompt() // 프롬포트 생성
                .user(message) // 사용자 메세지
                .call() // 호출
                .content(); // 요청정보를 받는 부분
    }

    public String chatmessage(String message) {
        return chatClient.prompt()
                .user(message) //
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getContent();
    }
}
