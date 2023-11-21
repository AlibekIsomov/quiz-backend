package com.quiz.service.withoutDTO;


import com.quiz.dto.QuestionDTO;
import com.quiz.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface QuestionService extends CommonService<Question>{

     Page<Question> search(String key, Pageable pageable);

    Optional<Question> create(QuestionDTO data) throws Exception;

    Optional<Question> update(Long id, QuestionDTO data) throws Exception;
}
