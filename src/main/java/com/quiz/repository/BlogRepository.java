package com.quiz.repository;


import com.quiz.entity.Blog;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends DistributedRepository<Blog>{
}
