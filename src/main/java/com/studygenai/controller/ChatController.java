package com.studygenai.controller;

import com.studygenai.dto.ChatRequest;
import com.studygenai.dto.ChatResponse;
import com.studygenai.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/chat/{id}")
    public ChatResponse askQuestion(
            @PathVariable Long id,
            @RequestBody ChatRequest request) {

        String answer =
                chatService.askQuestion(id, request.getQuestion());

        return new ChatResponse(answer);
    }
}