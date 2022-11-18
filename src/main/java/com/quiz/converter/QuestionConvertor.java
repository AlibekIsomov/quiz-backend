package com.quiz.converter;

import com.quiz.dto.QuestionDTO;
import com.quiz.dto.UserDTO;
import com.quiz.entity.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionConvertor extends AbstractDTOConverter<Question, QuestionDTO>{
    @Override
    public QuestionDTO convert(Question entity) {
        QuestionDTO questionDTO = new QuestionDTO(entity);

        super.convert(entity, questionDTO);

        return questionDTO;
    }
}
