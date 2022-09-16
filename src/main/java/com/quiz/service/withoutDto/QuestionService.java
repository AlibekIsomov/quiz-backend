package com.quiz.service.withoutDto;

import com.quiz.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface QuestionService extends CommonService<Question> {
    public Page<Question> search(String key, Pageable pageable);
}
