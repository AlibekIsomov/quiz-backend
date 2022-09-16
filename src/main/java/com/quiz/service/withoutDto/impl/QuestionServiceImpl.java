package com.quiz.service.withoutDto.impl;

import com.quiz.entity.Question;
import com.quiz.repository.DistributedRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.withoutDto.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl extends AbstractService<Question> implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    public QuestionServiceImpl(DistributedRepository<Question> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Question question) {

    }

    @Override
    public void someChangesForUpdate(Question question) {

    }

    @Override
    public Page<Question> search(String key, Pageable pageable) {
        return null;
    }
}
