package com.quiz.controller;



import com.quiz.dto.QuestionDTO;
import com.quiz.dto.QuestionLevelDTO;
import com.quiz.entity.Question;
import com.quiz.entity.QuestionLevel;
import com.quiz.service.withoutDTO.QuestionLevelService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/level")
public class  QuestionLevelController  {
    @Autowired
    QuestionLevelService questionLevelService;

    @PostMapping
    public ResponseEntity<QuestionLevel> create(@RequestBody QuestionLevelDTO data) {
        try {
            Optional<QuestionLevel> question = questionLevelService.create(data);

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
    public ResponseEntity<QuestionLevel> update(@PathVariable Long id,
                                           @RequestBody QuestionLevelDTO data) {
        try {
            Optional<QuestionLevel> updatedInventory = questionLevelService.update(id, data);

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
    public ResponseEntity<Page<QuestionLevel>> getAll(Pageable pageable) throws Exception {
        return ResponseEntity.ok(questionLevelService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionLevel> getById(@PathVariable Long id) throws Exception {
        return questionLevelService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        questionLevelService.deleteById(id);
    }
}
