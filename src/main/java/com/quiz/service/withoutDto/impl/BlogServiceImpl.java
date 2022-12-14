package com.quiz.service.withoutDto.impl;

import com.quiz.entity.Blog;
import com.quiz.repository.DistributedRepository;
import com.quiz.service.withoutDto.BlogService;

import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends AbstractService<Blog> implements BlogService {


    public BlogServiceImpl(DistributedRepository<Blog> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Blog entity) {

    }

    @Override
    public void someChangesForUpdate(Blog entity) {

    }
}
