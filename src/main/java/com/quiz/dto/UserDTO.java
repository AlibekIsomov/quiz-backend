package com.quiz.dto;


import com.quiz.entity.Role;
import com.quiz.entity.User;

import java.util.Set;

public class UserDTO extends BaseDTO {

    private String name;
    private String surname;
    private String username;
    private String email;
    private Set<Role> roles;
    private Boolean active;
    private String number;



    public UserDTO() {

    }



    public UserDTO(User user) {

        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.roles = user.getRoles();
        this.active = user.getActive();
        this.number = user.getNumber();
        this.roles = user.getRoles();
        this.active = user.getActive();
    }



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}