package com.studygenai.dto;

import java.util.List;

public class FlashcardResponse {

    private List<String> flashcards;

    public FlashcardResponse() {
    }

    public FlashcardResponse(List<String> flashcards) {
        this.flashcards = flashcards;
    }

    public List<String> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<String> flashcards) {
        this.flashcards = flashcards;
    }
}