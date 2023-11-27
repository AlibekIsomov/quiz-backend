package com.quiz.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.of(loggedInUsername);
    }

}