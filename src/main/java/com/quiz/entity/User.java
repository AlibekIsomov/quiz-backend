package com.quiz.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
    @Table(name = "users")
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

        @Column(nullable = false, unique = true)
        private String number;

        private Boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private Set<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
