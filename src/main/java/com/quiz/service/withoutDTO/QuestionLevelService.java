package com.quiz.service.withoutDTO;

import com.quiz.entity.QuestionLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionLevelService extends CommonService<QuestionLevel> {
    public Page<QuestionLevel> search(String key, Pageable pageable);
    public void deleteQuestionLevel(Long id);
}
