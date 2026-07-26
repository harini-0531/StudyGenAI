package com.studygenai.service;

import com.studygenai.repository.UploadedDocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private UploadedDocumentRepository repository;

    @Autowired
    private GroqService geminiService;

    public String askQuestion(Long id, String question) {

        repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Document not found"));

        return geminiService.askGroq(question);
    }
}