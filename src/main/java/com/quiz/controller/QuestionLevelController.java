package com.quiz.controller;


import com.quiz.entity.QuestionLevel;
import com.quiz.service.withoutDTO.CommonService;
import com.quiz.service.withoutDTO.OverAllService;
import com.quiz.service.withoutDTO.QuestionLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/level")
public class  QuestionLevelController extends AbstractController<QuestionLevel> {
    public QuestionLevelController(CommonService<QuestionLevel> service) {
        super(service);
    }

    @Autowired
    QuestionLevelService questionLevelService;
    @Autowired
    OverAllService overAllService;
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestionLevel(@PathVariable Long id){
        questionLevelService.deleteQuestionLevel(id);
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key, Pageable pageable) {
        return ResponseEntity.ok(questionLevelService.search(key, pageable));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(Pageable pageable){
        return ResponseEntity.ok(questionLevelService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(questionLevelService.getById(id));

    }
}
