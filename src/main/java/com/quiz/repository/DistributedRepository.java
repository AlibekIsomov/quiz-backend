package com.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DistributedRepository<ENTITY> extends JpaRepository<ENTITY, Long> {
    public Page<ENTITY> findAllByOrderByIdDesc(Pageable pageable);
}
