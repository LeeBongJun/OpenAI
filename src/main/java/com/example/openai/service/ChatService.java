package com.example.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateChatResponse2() {
        return chatClient.prompt()
                .user("뉴턴의 운동 제2법칙을 간단하게 설명하세요.")
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }

    public String chat(String message) {

        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();

    }

    public String chatplace(String subject, String tone, String message) {

        return chatClient.prompt()
                .user(message)
                .system(sp -> sp.
                        param("subject" , subject )
                        .param("tone" , tone))
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();

    }

    public ChatResponse chatjson(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
    }
}
