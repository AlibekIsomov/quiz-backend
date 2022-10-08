package com.quiz.entity;


import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Exam extends DistributedEntity{

    @NotNull
    private Long numberOf;
    @NotNull
    private Long overall;


    @ManyToOne
    @JoinTable(
            name = "exam_question",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Question question;


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(Long numberOf) {
        this.numberOf = numberOf;
    }

    public Long getOverall() {
        return overall;
    }

    public void setOverall(Long overall) {
        this.overall = overall;
    }
}
