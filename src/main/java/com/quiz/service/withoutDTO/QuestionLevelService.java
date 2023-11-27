package com.quiz.service.withoutDTO;

import com.quiz.dto.QuestionLevelDTO;
import com.quiz.entity.QuestionLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QuestionLevelService {

    Page<QuestionLevel> getAll(Pageable pageable) throws Exception;

    Optional<QuestionLevel> create(QuestionLevelDTO data) throws Exception;

    Optional<QuestionLevel> update(Long id, QuestionLevelDTO data) throws Exception;

    Optional<QuestionLevel> getById(Long id) throws Exception;

    void deleteById(Long id);
}
