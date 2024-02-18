package com.rentu.rentu.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    @ManyToOne
    private Location location;
    @OneToMany
    private List<Vehicle> vehicles;
    @OneToMany(mappedBy = "agency")
    @JsonManagedReference
    private List<Reservation> reservations;

    // Default constructor
    public Agency() {
    }

    // Constructor with parameters
    public Agency(Long id, String name, Location location, List<Vehicle> vehicles, String email) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vehicles = vehicles;
        this.email = email;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
