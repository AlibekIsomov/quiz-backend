package com.quiz.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class DistributedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @JsonFormat(pattern = "yyyy.MM.dd.HH.mm")
//    @DateTimeFormat(pattern = "yyyy.MM.dd.HH.mm")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    private LocalDateTime modified;
//    private LocalDateTime created;

    public DistributedEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public LocalDateTime getModified() {
//        return modified;
//    }
//
//    public void setModified(LocalDateTime modified) {
//        this.modified = modified;
//    }
//
//    public LocalDateTime getCreated() {
//        return created;
//    }
//
//    public void setCreated(LocalDateTime created) {
//        this.created = created;
//    }

    public boolean isNewEntity() {
        return this.id == null;
    }
}
