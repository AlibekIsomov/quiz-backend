package com.quiz.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.quiz.service.withoutDto.OverAllService;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Question extends DistributedEntity {
    @NotNull
    private String title;

    @NotNull
    private String answer;

    @ManyToOne
    private QuestionLevel questionLevel;
    @ManyToOne
    private Overall overAll;

    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Overall getOverAll() {
        return overAll;
    }

    public void setOverAll(Overall overAll) {
        this.overAll = overAll;
    }
}
