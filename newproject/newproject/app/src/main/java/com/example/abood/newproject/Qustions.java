package com.example.abood.newproject;

import java.util.List;

public class Qustions {

    private String quastionText ;
    private List<String> choices;
    private String correctAnswer;
    private String photo;


    public String getQuastionText() {
        return quastionText;
    }

    public void setQuastionText(String quastionText) {
        this.quastionText = quastionText;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
