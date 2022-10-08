package com.quiz.service.withoutDto;

import com.quiz.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamService extends CommonService<Exam>{
    public Page<Exam> search(String key, Pageable pageable);
}
