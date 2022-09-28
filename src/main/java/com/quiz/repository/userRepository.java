package com.quiz.repository;

import com.quiz.entity.Role;
import com.quiz.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends DistributedRepository<User> {
    public Page<User> findAllByIdOrNameContainsIgnoreCaseOrSurnameContainsIgnoreCaseOrUsernameContainsIgnoreCaseOrEmailContainsIgnoreCase(Long id, String name, String surname, String username, String email, Pageable pageable);

    Optional<User> findByUsername(String username);

    List<User> findAllByRolesContains(Role role);

}
