package com.rentu.rentu.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @OneToMany
    private List<Vehicle> vehicles;

    // Default constructor
    public Agency() {
    }

    // Constructor with parameters
    public Agency(Long id, String name, String address, List<Vehicle> vehicles) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.vehicles = vehicles;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

}
