package com.quiz.repository;

import com.quiz.entity.Fayl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaylRepository extends JpaRepository<Fayl, Long> {
}
