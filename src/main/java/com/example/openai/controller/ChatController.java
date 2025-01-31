package com.example.openai.controller;

import com.example.openai.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 클라이언트 요청을 받아서 JSON형식으로 응답하는 컨트롤러
@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // LLM(gpt-4o)와 통신할 수 있는 객체 : ChatClient
    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatService.chat(message);
    }

    @GetMapping("/chatmessage")
    public String chatmessage(@RequestParam("message") String message) {
        return chatService.chatmessage(message);
    }
}
