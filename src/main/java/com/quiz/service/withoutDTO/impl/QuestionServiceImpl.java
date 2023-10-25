package com.quiz.service.withoutDTO.impl;

import com.quiz.entity.Question;
import com.quiz.repository.DistributedRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.withoutDTO.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Service
@Transactional
public class QuestionServiceImpl extends AbstractService<Question> implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public QuestionServiceImpl(DistributedRepository<Question> repository) {
        super(repository);
    }

    @Override
    public Page<Question> search(String key, Pageable pageable) {
        try{
            Long n=Long.parseLong(key);
            return questionRepository.findAllByTitleContainingIgnoreCaseOrQuestionLevel(n,key,key,  pageable);
        }
        catch (Exception x) {
            return questionRepository.findAllByTitleContainingIgnoreCaseOrQuestionLevel((long)-1, key,  key, pageable);
        }
    }
    @Override
    public void someChangesForCreate(Question entity) {

    }

    @Override
    public void someChangesForUpdate(Question entity) {

    }


}

