package com.quiz.service.withoutDTO.impl;

import com.quiz.dto.BlogDTO;
import com.quiz.entity.Blog;
import com.quiz.entity.FileEntity;
import com.quiz.repository.BlogRepository;
import com.quiz.repository.FileRepository;
import com.quiz.service.withoutDTO.BlogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    @Autowired
    FileRepository fileRepository;
    @Autowired
    BlogRepository blogRepository;

    @Override
    public Optional<Blog> create(BlogDTO data) throws Exception {

        Optional<FileEntity> optionalFileEntity = fileRepository.findById(data.getFileEntityId());
        if (!optionalFileEntity.isPresent()) {
            logger.info("Such ID File does not exist!");
        }

        Blog blog = new Blog();
        blog.setName(data.getName());
        blog.setLink(data.getLink());
        blog.setDescription(data.getDescription());
        blog.setFileEntity(optionalFileEntity.get());

        return Optional.of(blogRepository.save(blog));
    }

    @Override
    public Optional<Blog> update(Long id, BlogDTO data) throws Exception {
        Optional<Blog> exsitingblog = blogRepository.findById(id);
        Optional<FileEntity> optionalFileEntity = fileRepository.findById(data.getFileEntityId());
        if (!exsitingblog.isPresent()) {
            logger.info("Blog with id " + id + " does not exist");
            return null;
        }
        Blog blog = exsitingblog.get();


        blog.setName(data.getName());
        blog.setLink(data.getLink());
        blog.setDescription(data.getDescription());
        blog.setFileEntity(optionalFileEntity.get());


        return Optional.of(blogRepository.save(blog));
    }
    @Override
    public Page<Blog> getAll(Pageable pageable) throws Exception {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> getById(Long id) throws Exception {
        if (!blogRepository.existsById(id)) {
            logger.error("Blog with id " + id + " does not exists");
            return null;
        }
        return blogRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        if (!blogRepository.existsById(id)) {
            logger.error("Blog level with id " + id + " does not exists");
        }

        blogRepository.deleteById(id);

    }
}
