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
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 500, min = 1)
    private String title;

    @Size(max = 400, min = 1)
    private String answer;

    @ManyToOne
    private QuestionLevel questionLevel;

    @OneToOne
    private FileEntity fileEntity;

    @CreatedDate
    private Instant createdAt;


}
