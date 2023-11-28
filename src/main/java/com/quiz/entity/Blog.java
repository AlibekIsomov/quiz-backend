package com.quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        @Size(max = 15, min = 1)
    private String name;

    @Size(max = 100, min = 5)
    private String description;

    private String link;

    @OneToOne
    private FileEntity fileEntity;

    @CreatedDate
    private Instant createdAt;

}
