package com.quiz.controller;

import com.quiz.entity.DistributedEntity;
import com.quiz.service.withoutDTO.CommonService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class  AbstractControllerCU<ENTITY extends DistributedEntity>{
    private final CommonService<ENTITY> service;

    public AbstractControllerCU(CommonService<ENTITY> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }
//    @PostMapping


    @DeleteMapping("/get/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(service.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
}