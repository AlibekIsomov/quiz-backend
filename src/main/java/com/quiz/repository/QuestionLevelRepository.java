package com.quiz.repository;


import com.quiz.entity.Question;
import com.quiz.entity.QuestionLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionLevelRepository extends DistributedRepository<QuestionLevel> {

    Page<QuestionLevel> findAllByLevelContainingIgnoreCase(Long id, String key, Pageable pageable);
}
