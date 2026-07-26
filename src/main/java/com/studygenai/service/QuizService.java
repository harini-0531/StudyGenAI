package com.studygenai.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygenai.dto.QuizQuestion;
import com.studygenai.model.UploadedDocument;
import com.studygenai.repository.UploadedDocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private UploadedDocumentRepository repository;

    @Autowired
    private GroqService groqService;

    public List<QuizQuestion> generateQuiz(
            Long id,
            int count,
            String difficulty) {

        // Allow only 5 or 10 questions
        if (count != 5 && count != 10) {

            throw new RuntimeException(
                    "Only 5 or 10 questions are supported."
            );
        }

        UploadedDocument document =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Document not found"));

        String text =
                document.getExtractedText();

        // Limit text size to avoid Groq token limit errors
        if(text.length() > 500) {
    text = text.substring(0, 500);
}
System.out.println("QUIZ TEXT LENGTH = " + text.length());

        String difficultyRule = "";

        if (difficulty.equalsIgnoreCase("easy")) {

            difficultyRule =
                    "Create simple factual questions.";

        } else if (difficulty.equalsIgnoreCase("medium")) {

            difficultyRule =
                    "Create conceptual understanding questions.";

        } else if (difficulty.equalsIgnoreCase("hard")) {

            difficultyRule =
                    "Create analytical and application-based questions.";
        }

        String prompt =
                """
                Generate %d multiple choice questions.

                %s

                Return ONLY valid JSON.

                Format:

                [
                  {
                    "question":"Question",
                    "optionA":"Option A",
                    "optionB":"Option B",
                    "optionC":"Option C",
                    "optionD":"Option D",
                    "answer":"A"
                  }
                ]

                Study Notes:

                %s
                """.formatted(
                        count,
                        difficultyRule,
                        text
                );

        String response =
                groqService.askGroq(prompt);

        System.out.println("=== QUIZ RESPONSE ===");
        System.out.println(response);
        System.out.println("=====================");

        try {

            response = response
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            int start =
                    response.indexOf("[");

            int end =
                    response.lastIndexOf("]");

            if (start >= 0 && end > start) {

                response =
                        response.substring(
                                start,
                                end + 1
                        );
            }

            ObjectMapper mapper =
                    new ObjectMapper();

            return mapper.readValue(
                    response,
                    new TypeReference<List<QuizQuestion>>() {
                    }
            );

        } catch (Exception e) {

            System.out.println("JSON PARSE ERROR");
            e.printStackTrace();

            throw new RuntimeException(
                    "Failed to parse quiz JSON",
                    e
            );
        }
    }
}