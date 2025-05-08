package com.example.openai.controller;

import com.example.openai.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 클라이언트 요청을 받아서 Json 형식으로 응답
@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message")String message) {
        return chatService.chat(message);
    }

    @GetMapping("/chatmessage")
    public String chatmessage() {
        return chatService.generateChatResponse2();
    }

    @GetMapping("/chatplace")
    public String chatplace(String subject , String tone , String message) {
        return chatService.chatplace(subject , tone , message);
    }


}
