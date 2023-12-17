package com.quiz.controller;


import com.quiz.dto.QuestionDTO;
import com.quiz.entity.Question;
import com.quiz.repository.FileRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.withoutDTO.QuestionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    FileRepository fileRepository;
    @Autowired
    QuestionRepository questionRepository;

    private volatile boolean isBlocked = false;

    @PostMapping("/block")
    public ResponseEntity<String> blockAPI() {
        isBlocked = !isBlocked; // Toggle block status

        if (isBlocked) {
            return ResponseEntity.ok("API Blocked");
        } else {
            return ResponseEntity.ok("API Unblocked");
        }
    }

    @GetMapping("/block")
    public ResponseEntity<String> checkBlockedStatus() {
        if (isBlocked) {
            return ResponseEntity.ok("API Blocked");
        } else {
            return ResponseEntity.ok("API Unblocked");
        }
    }


    @PostMapping
    public ResponseEntity<Question> create(@RequestBody QuestionDTO data) {
        try {
            Optional<Question> question = questionService.create(data);

            if  (question.isPresent()){
                return ResponseEntity.ok(question.get());
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> update(@PathVariable Long id,
                                       @RequestBody QuestionDTO data) {
        try {
            Optional<Question> updatedInventory = questionService.update(id, data);

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
    public ResponseEntity<Page<Question>> getAll(Pageable pageable) throws Exception {
        return ResponseEntity.ok(questionService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getById(@PathVariable Long id) throws Exception {
        return questionService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        questionService.deleteById(id);
    }
}

