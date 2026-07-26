package com.studygenai.controller;

import com.studygenai.service.GroqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GroqTestController {

    @Autowired
    private GroqService groqService;

    @GetMapping("/groq-test")
    public String test() {

        return groqService.askGroq(
                "Explain Artificial Intelligence in 3 lines."
        );
    }
}