package com.quiz.controller;

import com.quiz.dto.BlogDTO;
import com.quiz.entity.Blog;
import com.quiz.repository.BlogRepository;
import com.quiz.service.withoutDTO.BlogService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    BlogService blogService;

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

    @GetMapping
    public ResponseEntity<Page<Blog>> getAll(Pageable pageable) throws Exception {
        return ResponseEntity.ok(blogService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getById(@PathVariable Long id) throws Exception {
        return blogService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        blogService.deleteById(id);
    }

}
