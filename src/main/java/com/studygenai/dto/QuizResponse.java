package com.studygenai.dto;

import java.util.List;

public class QuizResponse {

    private List<QuizQuestion> questions;

    public QuizResponse() {
    }

    public QuizResponse(List<QuizQuestion> questions) {
        this.questions = questions;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }
}