package com.quiz.dto;

import com.quiz.entity.Question;
import com.quiz.entity.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO extends BaseDTO {
    private Long id;
    private String title;
    private Long questionLevelId;
    private Long fileEntityId;


}
