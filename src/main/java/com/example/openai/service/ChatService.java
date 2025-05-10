package com.example.openai.service;

import com.example.openai.entity.Answer;
import com.example.openai.entity.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public Answer chatobject(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(Answer.class);
    }

    private final String recipeTemplate =  """
    Answer for {foodname} for {question}?
    """;


    public Answer recipe(String foodName, String question) {
        return chatClient.prompt()
                .user(userSpec -> userSpec.text(recipeTemplate)
                        .param("foodname" , foodName)
                        .param("question" , question)
                )
                .call()
                .entity(Answer.class);
    }

    public List<String> chatlist(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }

    public Map<String, String> chatmap(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<Map<String, String>>(){});
    }

    public List<Movie> chatmovie(String directorName) {
        String template= """
                     "Generate a list of movies directed by {directorName}. If the director is unknown, return null.
                     한국 영화는 한글로 표기해줘.
                     Each movie should include a title and release year. {format}"
                     """;

        List<Movie> movieList = chatClient.prompt()
                .user(userSpec -> userSpec.text(template)
                        .param("directorName" , directorName)
                        .param("format" , "json"))
                .call()
                .entity(new ParameterizedTypeReference<List<Movie>>() {});

        return  movieList;
    }
}
