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


}
