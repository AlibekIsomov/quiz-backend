package com.quiz.service.withoutDTO.impl;

import com.quiz.entity.Blog;
import com.quiz.repository.DistributedRepository;
import com.quiz.service.withoutDTO.BlogService;

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
