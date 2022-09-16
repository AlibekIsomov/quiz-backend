package com.quiz.entity;


import com.sun.istack.NotNull;

import javax.persistence.Entity;

@Entity
public class Question extends DistributedEntity {
    @NotNull
    private String title;

    @NotNull
    private String answer;

        private QuestionLevel questionLevel;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionLevel getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(QuestionLevel questionLevel) {
        this.questionLevel = questionLevel;
    }
}
