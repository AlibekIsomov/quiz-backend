package com.quiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quiz.entity.DistributedEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "userser")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
public class User extends DistributedEntity {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Size(max = 30, min = 6)
    @Column(unique = true, nullable = false)
    private String username;

    @Size(max = 60, min = 60)
    @Column(nullable = false)
    private String password;

    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private Set<Role> roles;

    private Boolean active;


}

