package com.quiz.dto;

import com.quiz.entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {

    private String name;

    private String description;

    private String link;

    private Long fileEntityId;


}
