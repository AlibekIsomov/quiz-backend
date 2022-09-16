package com.quiz.controller.withoutDto;


import com.quiz.entity.Question;
import com.quiz.service.withoutDto.CommonService;
import com.quiz.service.withoutDto.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/question")
public class QuestionController extends AbstractController<Question> {
    @Autowired
    QuestionService questionService;

    public QuestionController(CommonService<Question> service) {
        super(service);
    }
    @RequestMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key, Pageable pageable){
        return ResponseEntity.ok(questionService.search(key, pageable));
}}

