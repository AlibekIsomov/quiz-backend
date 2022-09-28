package com.quiz.controller.withDto;


import com.quiz.dto.BaseDTO;
import com.quiz.entity.DistributedEntity;
import com.quiz.service.witDto.CommonServiceDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractDTOController<ENTITY extends DistributedEntity, DTO extends BaseDTO> {
    private final CommonServiceDto<ENTITY, DTO> service;

    public AbstractDTOController(CommonServiceDto<ENTITY, DTO> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ENTITY entity) throws Exception {
        return new ResponseEntity<>(service.create(entity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ENTITY entity) throws Exception {
        return new ResponseEntity<>(service.update(entity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
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
