package com.studygenai.service;

import com.studygenai.model.UploadedDocument;
import com.studygenai.repository.UploadedDocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    @Autowired
    private UploadedDocumentRepository repository;

    @Autowired
    private GroqService groqService;

    public String generateSummary(
            Long id,
            String type) {

        UploadedDocument document =
                repository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Document not found"
                                )
                        );

        String text =
                document.getExtractedText();

        if(text.length() > 3000) {
            text = text.substring(0, 3000);
        }

        String prompt = "";

        if(type.equalsIgnoreCase("Short")) {

            prompt =
                    "Generate a SHORT summary in 5-8 lines.\n\n"
                            + text;

        }
        else if(type.equalsIgnoreCase("Medium")) {

            prompt =
                    "Generate a MEDIUM summary in 2-3 paragraphs.\n\n"
                            + text;

        }
        else {

            prompt =
                    "Generate a DETAILED summary covering all important concepts.\n\n"
                            + text;

        }

        return groqService.askGroq(prompt);
    }
}