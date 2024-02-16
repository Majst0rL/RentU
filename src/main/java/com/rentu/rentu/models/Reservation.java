package com.rentu.rentu.models;

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

    @ManyToOne
    private User user;

    @ManyToOne
    private Vehicle vehicle;


    public Reservation(LocalDate startDate, LocalDate endDate, User user, Vehicle vehicle) {
        if (!user.getLicence()) {
            throw new IllegalArgumentException("Uporabnik nima veljavne licence.");
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
        return days * vehicle.getPricePerDay();
    }

    //setters and getters
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setPrice(double price) {
        this.price = price;
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
}

