package com.quiz.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
public class Question extends DistributedEntity {
    @NotNull
    private String title;

    @NotNull
    private String answer;

    @NotNull
    @ManyToOne
    private QuestionLevel questionLevel;

    @ManyToOne
    private FileEntity photo;




}
