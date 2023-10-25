package com.quiz.service.withDTO.Impl;

import com.quiz.converter.AbstractDTOConverter;
import com.quiz.dto.QuestionDTO;
import com.quiz.entity.Question;
import com.quiz.repository.DistributedRepository;
import com.quiz.service.withDTO.QuestionDTOService;
import org.springframework.stereotype.Service;

@Service
public class QuestionDTOServiceImpl extends AbstractDTOService<Question, QuestionDTO> implements QuestionDTOService {
    public QuestionDTOServiceImpl(DistributedRepository<Question> repository, AbstractDTOConverter<Question, QuestionDTO> converter) {
        super(repository, converter);
    }

    @Override
    public void someChangesForCreate(Question entity) {

    }

    @Override
    public void someChangesForUpdate(Question entity) {

    }
}
