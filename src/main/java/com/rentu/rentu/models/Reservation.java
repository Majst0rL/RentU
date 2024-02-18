package com.rentu.rentu.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Agency agency;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne
    private Vehicle vehicle;


    public Reservation(LocalDate startDate, LocalDate endDate, User user, Vehicle vehicle) {
        if (!user.getLicence()) {
            throw new IllegalArgumentException("The user does not have a valid driving license");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.vehicle = vehicle;
        this.price = calculatePrice();
    }

    public Reservation() {

    }

    public double calculatePrice() {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        double price = days * vehicle.getPricePerDay();
        return price;
    }

    //setters and getters
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setPrice() {
        this.price = calculatePrice();
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    @PostLoad
    public void onPostLoad() {
        price = calculatePrice();
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Long getId() {
        return id;
    }
}

