package com.quiz.dto;

import java.time.LocalDateTime;

public class BaseDTO {
    private Long id;
    private LocalDateTime modified;
    private LocalDateTime created;

    public BaseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
