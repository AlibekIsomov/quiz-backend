package com.quiz.repository;

import com.quiz.entity.Overall;
import com.quiz.entity.Question;
import com.quiz.entity.QuestionLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends DistributedRepository<Question> {
    public Page<Question> findAllByTitleContainingIgnoreCaseOrQuestionLevel(Long id ,String Title, String QuestionLevel, Pageable pageable );
    public List<Question> findAllByQuestionLevelId(Long id);
}
