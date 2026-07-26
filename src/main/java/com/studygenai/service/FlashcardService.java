package com.studygenai.service;

import com.studygenai.model.UploadedDocument;
import com.studygenai.repository.UploadedDocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FlashcardService {

    @Autowired
    private UploadedDocumentRepository repository;

    @Autowired
    private GroqService groqService;

    public List<String> generateFlashcards(
            Long id,
            int count) {

        UploadedDocument document =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Document not found"
                                ));

        String text =
                document.getExtractedText();

        if(text.length() > 3000) {
            text = text.substring(0, 3000);
        }

        String prompt =
        "Generate exactly "
        + count
        + " study flashcards.\n\n"

        + "Rules:\n"
        + "1. Question must be short.\n"
        + "2. Answer must be complete.\n"
        + "3. Format exactly:\n"
        + "Question | Answer\n"
        + "4. One flashcard per line.\n"
        + "5. No numbering.\n"
        + "6. Do not place answer text before '|'.\n\n"

        + text;
        String aiResponse =
                groqService.askGroq(prompt);
                System.out.println(aiResponse);

        return Arrays.stream(aiResponse.split("\\r?\\n"))
        .filter(line -> !line.trim().isEmpty())
        .toList();
    
    }
}