package com.example.e_exam.model;

public class Content {
    String QuestionContent;

    public Content() {
    }

    public Content(String questionContent) {
        QuestionContent = questionContent;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }
}
