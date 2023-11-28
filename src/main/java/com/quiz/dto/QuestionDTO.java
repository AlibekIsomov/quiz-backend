package com.quiz.dto;

import com.quiz.entity.Question;
import com.quiz.entity.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO  {
    private String title;

    private String answer;

    private Long questionLevelId;

    private Long fileEntityId;
}
