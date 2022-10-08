package com.quiz.controller.withoutDto;


import com.quiz.entity.Exam;
import com.quiz.service.withoutDto.CommonService;
import com.quiz.service.withoutDto.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/exam")
public class ExamController extends AbstractController<Exam> {
    @Autowired
    ExamService examService;

    public ExamController(CommonService<Exam> service) {
        super(service);
    }
    @RequestMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key, Pageable pageable){
        return ResponseEntity.ok(examService.search(key, pageable));
}
}
