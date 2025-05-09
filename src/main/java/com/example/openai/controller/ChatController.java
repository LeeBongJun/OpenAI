package com.example.openai.controller;

import com.example.openai.entity.Answer;
import com.example.openai.service.ChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/chatjson")
    public ChatResponse ChatResponse(String message) {
        return chatService.chatjson(message);
    }

    @GetMapping("/chatobject")
    public Answer chatobject(String message) {
        return chatService.chatobject(message);
    }

    @GetMapping("/recipe")
    public Answer recipe(String foodName , String question) {
        return chatService.recipe(foodName , question);
    }

    @GetMapping("/chatlist")
    public List<String> chatList(String message) {
        return chatService.chatlist(message);
    }

    @GetMapping("/chatmap")
    public Map<String , String> chatmap(String message) {
        return chatService.chatmap(message);
    }

}
