package com.example.e_exam.model;

public class Subject {
    String Difficulty;
    String QuestionContent;
    String QuestionId;
    String Type;
    String ValidAnswer;

    public Subject() {
    }

    public Subject(String difficulty, String questionContent, String questionId, String type, String validAnswer) {
        Difficulty = difficulty;
        QuestionContent = questionContent;
        QuestionId = questionId;
        Type = type;
        ValidAnswer = validAnswer;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }

    public String getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(String questionId) {
        QuestionId = questionId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getValidAnswer() {
        return ValidAnswer;
    }

    public void setValidAnswer(String validAnswer) {
        ValidAnswer = validAnswer;
    }
}
