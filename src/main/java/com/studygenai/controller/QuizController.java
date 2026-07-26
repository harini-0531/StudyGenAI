package com.studygenai.controller;

import com.studygenai.dto.QuizResponse;
import com.studygenai.dto.QuizQuestion;
import com.studygenai.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quiz/{id}")
    public QuizResponse getQuiz(
            @PathVariable Long id,
            @RequestParam(defaultValue = "5") int count,
            @RequestParam(defaultValue = "easy") String difficulty) {

        List<QuizQuestion> questions =
                quizService.generateQuiz(
                        id,
                        count,
                        difficulty
                );

        return new QuizResponse(
                questions
        );
    }
}