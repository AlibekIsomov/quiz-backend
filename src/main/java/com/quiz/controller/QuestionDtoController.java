package com.quiz.controller;


import com.quiz.dto.QuestionDTO;
import com.quiz.entity.Question;
import com.quiz.service.withDTO.CommonServiceDto;
import com.quiz.service.withDTO.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/questions")
public class QuestionDtoController extends AbstractDTOController<Question, QuestionDTO> {
    @Autowired
    QuestionDTOService questionDTOService;
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

    public QuestionDtoController(CommonServiceDto<Question, QuestionDTO> service) {
        super(service);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(Pageable pageable){
   if (isBlocked) {
 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("API is blocked");
 } else {
 // Process the request
       return ResponseEntity.ok(questionDTOService.getAll(pageable));
}

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(questionDTOService.getById(id));

    }
}