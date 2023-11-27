package com.quiz.service.withoutDTO;

import com.quiz.dto.BlogDTO;
import com.quiz.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogService {

    Optional<Blog> create(BlogDTO data) throws Exception;

    Optional<Blog> update(Long id, BlogDTO data) throws Exception;

    Page<Blog> getAll(Pageable pageable) throws Exception;

    Optional<Blog> getById(Long id) throws Exception;

    void deleteById(Long id);
}
