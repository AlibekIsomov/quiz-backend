package com.quiz.dto;

import com.quiz.entity.User;

import java.util.Set;

public class UserDTO extends BaseDTO {

    private String name;
    private String surname;
    private String username;
    private String number;

    public UserDTO() {

    }


    public UserDTO(User user) {

        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.number = user.getNumber();
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}