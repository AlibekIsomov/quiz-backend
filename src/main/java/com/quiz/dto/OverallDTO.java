package com.quiz.dto;

import com.quiz.entity.Overall;
import com.quiz.entity.QuestionLevel;

import javax.naming.Name;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

public class OverallDTO extends BaseDTO{
    private int score;
    private String point;
    private int attemptedQuestions;
    private LocalDate date;
    private String username;
    private String name;
    private String surname;
    @ManyToOne
    private QuestionLevel questionLevel;

    public OverallDTO() {
    }
    public OverallDTO(Overall overall) {
        this.score = overall.getScore();
        this.point = overall.getPoint();
        this.attemptedQuestions = overall.getAttemptedQuestions();
        this.date = overall.getDate();
        this.username = overall.getUser().getUsername();
        this.name = overall.getUser().getName();
        this.surname = overall.getUser().getSurname();
        this.questionLevel = overall.getQuestionLevel();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public int getAttemptedQuestions() {
        return attemptedQuestions;
    }

    public void setAttemptedQuestions(int attemptedQuestions) {
        this.attemptedQuestions = attemptedQuestions;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public QuestionLevel getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(QuestionLevel questionLevel) {
        this.questionLevel = questionLevel;
    }
}
