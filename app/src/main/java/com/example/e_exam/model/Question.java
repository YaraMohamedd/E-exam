package com.example.e_exam.model;

public class Question {
    String QuestionId;
    String Difficulty;
    String QuestionContent;
    String Type;
    String ValidAnswer;
    String WrongAnswer1;
    String WrongAnswer2;
    String WrongAnswer3;


    public Question() {
    }

    public Question(String difficulty, String questionContent,String questionId, String type, String validAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {
        QuestionId=questionId;
        Difficulty = difficulty;
        QuestionContent = questionContent;
        Type = type;
        ValidAnswer = validAnswer;
        WrongAnswer1 = wrongAnswer1;
        WrongAnswer2 = wrongAnswer2;
        WrongAnswer3 = wrongAnswer3;
    }

    public String getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(String questionId) {
        QuestionId = questionId;
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

    public String getWrongAnswer1() {
        return WrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        WrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return WrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        WrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return WrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        WrongAnswer3 = wrongAnswer3;
    }
}
