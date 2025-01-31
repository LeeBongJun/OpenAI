package com.example.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {

        return chatClientBuilder.defaultSystem("당신의 교육 튜터입니다. 개념을 명확하고 간단하게 설명하세요.").build();
    }
}
