package com.quiz.controller.withoutDto;

import com.quiz.entity.Blog;
import com.quiz.repository.BlogRepository;
import com.quiz.service.withoutDto.BlogService;
import com.quiz.service.withoutDto.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/blog")
public class BlogController extends AbstractController<Blog>{
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/imagedata";
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    BlogService blogService;
    public BlogController(CommonService<Blog> service) {
        super(service);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(Pageable pageable){
        return ResponseEntity.ok(blogService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(blogService.getById(id));

    }
    }
