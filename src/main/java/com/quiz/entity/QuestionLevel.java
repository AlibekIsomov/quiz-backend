package com.quiz.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class QuestionLevel extends DistributedEntity {
    @Column(unique = true, nullable = false, length = 30)
    private String level;

    private String info;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
