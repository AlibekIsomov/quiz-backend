package com.quiz.controller.withoutDto;


import com.quiz.entity.QuestionLevel;
import com.quiz.service.withoutDto.CommonService;
import com.quiz.service.withoutDto.QuestionLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/level")
public class  QuestionLevelController extends AbstractController<QuestionLevel> {
    public QuestionLevelController(CommonService<QuestionLevel> service) {
        super(service);
    }

    @Autowired
    QuestionLevelService questionLevelService;

    @RequestMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key, Pageable pageable) {
        return ResponseEntity.ok(questionLevelService.search(key, pageable));
    }
}
