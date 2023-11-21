package com.quiz.controller;


import com.quiz.dto.QuestionDTO;
import com.quiz.entity.Question;
import com.quiz.repository.FileRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.withoutDTO.CommonService;
import com.quiz.service.withoutDTO.QuestionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController extends AbstractControllerCU<Question>   {

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


    public QuestionController(CommonService<Question> service) {
        super(service);
    }
    @RequestMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key, Pageable pageable) {
        return ResponseEntity.ok(questionService.search(key, pageable));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(Pageable pageable){
        if (isBlocked) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("API is blocked");
        } else {
            // Process the request
            return ResponseEntity.ok(questionService.getAll(pageable));
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        if (isBlocked) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("API is blocked");
        } else {
            // Process the request
            return ResponseEntity.ok(questionService.getById(id));
        }


    }
    @PostMapping
    public ResponseEntity<Question> create(@RequestBody QuestionDTO data) {
        try {
            Optional<Question> question = questionService.create(data);

            if(question.isPresent()){
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
}

