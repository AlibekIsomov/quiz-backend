package com.quiz.service.withoutDTO;


import com.quiz.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface QuestionService extends CommonService<Question>{

     Page<Question> search(String key, Pageable pageable);

}
