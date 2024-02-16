package com.rentu.rentu.models;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Vehicle vehicle;
    @ManyToOne
    private Agency agency;
    private String description;

    public Post(){}
    public Post(Vehicle vehicle, String description, Agency agency) {
        this.vehicle = vehicle;
        this.description = description;
        this.agency = agency;
    }

    public void setId (Long id){
        this.id = id;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Long getId() {
        return id;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public String getDescription() {
        return description;
    }
    public Agency getAgency() {
        return agency;
    }
}
