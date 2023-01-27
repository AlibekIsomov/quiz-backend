package com.quiz.service.withoutDto.impl;

import com.quiz.entity.Overall;
import com.quiz.repository.DistributedRepository;
import com.quiz.service.withoutDto.OverAllService;
import org.springframework.stereotype.Service;

@Service
public class OverallServiceImpl  extends AbstractService<Overall> implements OverAllService {
    public OverallServiceImpl(DistributedRepository<Overall> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Overall entity) {

    }

    @Override
    public void someChangesForUpdate(Overall entity) {

    }
}
