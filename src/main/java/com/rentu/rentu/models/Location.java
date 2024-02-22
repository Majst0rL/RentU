package com.rentu.rentu.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int number;
    private String city;
    private String country;
    @OneToMany(mappedBy = "location")
    private List<User> users;

    public Location() {
    }

    public Location(String street, int number, String city, String country, List<User> users) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {this.country = country;}

    public List<User> getUsers() {return users;}

    public void setUsers(List<User> users) {this.users = users;}
}

