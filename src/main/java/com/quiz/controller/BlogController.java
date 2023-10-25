package com.quiz.controller;

import com.quiz.entity.Blog;
import com.quiz.repository.BlogRepository;
import com.quiz.service.withoutDTO.BlogService;
import com.quiz.service.withoutDTO.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getById(id));

    }

}
