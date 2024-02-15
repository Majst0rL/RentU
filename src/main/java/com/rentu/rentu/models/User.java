package com.rentu.rentu.models;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Boolean licence;
    private String username;
    private String password;
    private String gender;
    private String userRole;

    public User(String firstName, String lastName, Date birthday, Boolean licence,
                String username, String password, String gender, String userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.licence = licence;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.userRole = userRole;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getLicence() {
        return licence;
    }

    public void setLicence(Boolean licence) {
        this.licence = licence;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}