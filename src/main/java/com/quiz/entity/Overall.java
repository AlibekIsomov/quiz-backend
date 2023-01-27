package com.quiz.entity;


import javax.persistence.Entity;

@Entity
public class Overall extends DistributedEntity{
    private int Score;

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
