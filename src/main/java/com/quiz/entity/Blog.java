package com.quiz.entity;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
public class Blog extends DistributedEntity {
    @NotNull
    @Size(max = 15, min = 1)
    private String name;

    @NotNull
    @Size(max = 100, min = 5)
    private String description;

    private String link;

    @ManyToOne
    private FileEntity photo;

}
