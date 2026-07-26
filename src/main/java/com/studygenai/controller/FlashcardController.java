package com.studygenai.controller;

import com.studygenai.dto.FlashcardResponse;
import com.studygenai.service.FlashcardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FlashcardController {

    @Autowired
    private FlashcardService flashcardService;

    @GetMapping("/flashcards/{id}")
    public FlashcardResponse getFlashcards(
            @PathVariable Long id,
            @RequestParam int count) {

        List<String> flashcards =
                flashcardService.generateFlashcards(
                        id,
                        count
                );

        return new FlashcardResponse(
                flashcards
        );
    }
}