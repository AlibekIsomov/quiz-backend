package com.quiz.dto;

import com.quiz.entity.Question;
import com.quiz.entity.QuestionLevel;



public class QuestionDTO extends BaseDTO {
    private Long id;
    private String title;
    private QuestionLevel questionLevel;


    public QuestionDTO() {

    }

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.questionLevel = question.getQuestionLevel();

    }

    public QuestionDTO(Long id,String title, QuestionLevel questionLevel) {
        this.id = id;
        this.title = title;
        this.questionLevel = questionLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionLevel getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(QuestionLevel questionLevel) {
        this.questionLevel = questionLevel;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
