package com.quiz.service.withoutDTO.impl;

import com.quiz.dto.BlogDTO;
import com.quiz.entity.Blog;
import com.quiz.entity.FileEntity;
import com.quiz.repository.BlogRepository;
import com.quiz.repository.DistributedRepository;
import com.quiz.repository.FileRepository;
import com.quiz.service.withoutDTO.BlogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl extends AbstractService<Blog> implements BlogService {
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    @Autowired
    FileRepository fileRepository;
    @Autowired
    BlogRepository blogRepository;

    public BlogServiceImpl(DistributedRepository<Blog> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Blog entity) {

    }

    @Override
    public void someChangesForUpdate(Blog entity) {

    }

    @Override
    public Optional<Blog> create(BlogDTO data) throws Exception {

        Optional<FileEntity> optionalFileEntity = fileRepository.findById(data.getFileEntityId());
        if (!optionalFileEntity.isPresent()) {
            logger.info("Such ID category does not exist!");
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
            logger.info("Inventory with id " + id + " does not exist");
            return null;
        }
        Blog blog = exsitingblog.get();


        blog.setName(data.getName());
        blog.setLink(data.getLink());
        blog.setDescription(data.getDescription());
        blog.setFileEntity(optionalFileEntity.get());


        return Optional.of(blogRepository.save(blog));
    }
}
