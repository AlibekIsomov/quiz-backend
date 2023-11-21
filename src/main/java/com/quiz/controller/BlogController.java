package com.quiz.controller;

import com.quiz.dto.BlogDTO;
import com.quiz.entity.Blog;
import com.quiz.repository.BlogRepository;
import com.quiz.service.withoutDTO.BlogService;
import com.quiz.service.withoutDTO.CommonService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
public class BlogController extends AbstractControllerCU<Blog>{
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
    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody BlogDTO data) {
        try {
            Optional<Blog> blog = blogService.create(data);

            if(blog.isPresent()){
                return ResponseEntity.ok(blog.get());
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> update(@PathVariable Long id,
                                            @RequestBody BlogDTO data) {
        try {
            Optional<Blog> updatedInventory = blogService.update(id, data);

            if (updatedInventory.isPresent()) {
                return ResponseEntity.ok(updatedInventory.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NotFoundException categoryNotFoundException) {
            // Handle category not found exception, e.g., return a bad request response
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Handle other exceptions, e.g., log the error and return an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
