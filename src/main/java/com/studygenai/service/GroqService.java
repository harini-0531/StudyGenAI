package com.studygenai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    public String askGroq(String prompt) {

        try {

            String url =
                    "https://api.groq.com/openai/v1/chat/completions";

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody =
                    Map.of(
                            "model",
                            "llama-3.1-8b-instant",

                            "messages",
                            List.of(
                                    Map.of(
                                            "role",
                                            "user",
                                            "content",
                                            prompt
                                    )
                            )
                    );

            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate =
                    new RestTemplate();

            ResponseEntity<String> response =
                    restTemplate.postForEntity(
                            url,
                            request,
                            String.class
                    );

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root =
                    mapper.readTree(response.getBody());

            return root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

        } catch (Exception e) {

            e.printStackTrace();

            return "Groq Error: "
                    + e.getMessage();

        }
    }
}