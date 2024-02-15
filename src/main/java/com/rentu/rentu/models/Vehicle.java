package com.rentu.rentu.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String manufacturer;
    private String model;
    private int year;
    private double pricePerDay;
    private FuelType fuelType;
    private VehicleType vehicleType;
    private int power;

    public Vehicle() {
    }

    public Vehicle(Long id, String manufacturer, String model, int year, double pricePerDay, FuelType fuelType, VehicleType vehicleType, int power) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
        this.power = power;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return year == vehicle.year && Double.compare(pricePerDay, vehicle.pricePerDay) == 0 && power == vehicle.power && Objects.equals(id, vehicle.id) && Objects.equals(manufacturer, vehicle.manufacturer) && Objects.equals(model, vehicle.model) && fuelType == vehicle.fuelType && vehicleType == vehicle.vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, model, year, pricePerDay, fuelType, vehicleType, power);
    }
}
