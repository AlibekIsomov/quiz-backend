package com.quiz.service.withoutDto.impl;


import com.quiz.entity.DistributedEntity;
import com.quiz.entity.Exam;


import com.quiz.repository.DistributedRepository;
import com.quiz.repository.ExamRepository;
import com.quiz.service.withoutDto.ExamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl extends AbstractService<Exam> implements ExamService {
    @Autowired
    ExamRepository examRepository;

    public ExamServiceImpl(DistributedRepository<Exam> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Exam entity) {

    }

    @Override
    public void someChangesForUpdate(Exam entity) {

    }

    @Override
    public Page<Exam> search(String key, Pageable pageable) {
        try{
            Long n=Long.parseLong(key);
            return examRepository.findAllByOrderByIdDesc( pageable);
        }
        catch (Exception x) {
            return examRepository.findAllByOrderByIdDesc( pageable);
        }
    }



}
