package com.quiz.service.withoutDTO;

import com.quiz.dto.BlogDTO;
import com.quiz.entity.Blog;

import java.util.Optional;

public interface BlogService extends CommonService<Blog>{

    Optional<Blog> create(BlogDTO data) throws Exception;


    Optional<Blog> update(Long id, BlogDTO data) throws Exception;
}
