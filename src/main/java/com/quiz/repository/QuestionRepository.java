package com.quiz.repository;

import com.quiz.entity.Question;
import com.quiz.entity.QuestionLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends DistributedRepository<Question> {
    public Page<Question> findAllByTitleContainingIgnoreCaseOrQuestionLevel(Long id ,String Title, String QuestionLevel, Pageable pageable );
}
