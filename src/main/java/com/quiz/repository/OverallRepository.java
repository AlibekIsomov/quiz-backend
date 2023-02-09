package com.quiz.repository;

import com.quiz.entity.Overall;
import com.quiz.entity.QuestionLevel;
import com.quiz.entity.User;

import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public interface OverallRepository extends DistributedRepository<Overall> {
    // Set<Overall> findByQuestionLevel(QuestionLevel questionLevel);

    // Set<Overall> getOverallByUser(String user);

    // Set<Overall> getOverallByUsername(String username);

    Set<Overall> findByQuestionLevel(QuestionLevel questionLevel);
	
	Set<Overall> getOOverallsByUser(User user);
	
	Set<Overall> getOverallsByUsername(String username);
}
