package com.quiz.controller;

import com.quiz.entity.Overall;


import com.quiz.service.withoutDTO.CommonService;
import com.quiz.service.withoutDTO.OverAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/overall")
public class OverallController extends AbstractController<Overall> {

    @Autowired
    OverAllService overAllService;

    public OverallController(CommonService<Overall> service) {
        super(service);
    }

    @GetMapping("/get/questionl")
    public ResponseEntity<?> getOverallsOfQuestionLevel(@PathVariable Long id){
        return ResponseEntity.ok(overAllService.getOverallsOfQuestionLevel(id));
    }
    // get all overalls
    @GetMapping("/all")
    public ResponseEntity<?> getAll(Pageable pageable){
        return ResponseEntity.ok(overAllService.getAllDTO(pageable));
    }

    // add overall
    @PostMapping("/save")
    public ResponseEntity<Overall> add(@RequestBody Overall overall){
        return ResponseEntity.ok(this.overAllService.addOverallWithUser(overall));
    }

    // update overall
    @PutMapping("/")
    public ResponseEntity<Overall> update(@RequestBody Overall overall){
        return ResponseEntity.ok(this.overAllService.updateOverall(overall));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(overAllService.getById(id));
    }


}