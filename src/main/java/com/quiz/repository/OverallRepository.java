package com.quiz.repository;

import com.quiz.entity.Overall;
import com.quiz.entity.QuestionLevel;
import com.quiz.entity.User;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface OverallRepository extends DistributedRepository<Overall> {
	public List<Overall> findAllByQuestionLevelId(Long id);

}
