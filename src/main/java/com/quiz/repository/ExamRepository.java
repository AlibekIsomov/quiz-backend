package com.quiz.repository;

import com.quiz.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends DistributedRepository<Exam> {

}
