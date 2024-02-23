package com.rentu.rentu.controllers;

import com.rentu.rentu.dao.AgencyRepository;
import com.rentu.rentu.dao.LocationRepository;
import com.rentu.rentu.dao.VehicleRepository;
import com.rentu.rentu.models.Agency;
import com.rentu.rentu.models.Location;
import com.rentu.rentu.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agencies")
@CrossOrigin
public class AgencyController {
    private final AgencyRepository agencyRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public AgencyController(AgencyRepository agencyRepository, VehicleRepository vehicleRepository, LocationRepository locationRepository) {
        this.agencyRepository = agencyRepository;
        this.vehicleRepository = vehicleRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public List<Agency> getAllAgencies() {
        return (List<Agency>) agencyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agency> getAgencyById(@PathVariable Long id) {
        Optional<Agency> agency = agencyRepository.findById(id);
        return agency.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createAgency(@RequestBody Agency agency) {
        Location location = agency.getLocation();
        if (location != null && location.getId() == null) {
            Location savedLocation = locationRepository.save(location);
            agency.setLocation(savedLocation);
        }

        List<Vehicle> existingOrNewVehicles = new ArrayList<>();
        for (Vehicle vehicle : agency.getVehicles()) {
            Optional<Vehicle> optionalExistingVehicle = vehicleRepository.findById(vehicle.getId());

            if (optionalExistingVehicle.isPresent()) {
                existingOrNewVehicles.add(optionalExistingVehicle.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vehicle with ID " + vehicle.getId() + " is not in the database yet.");
            }
        }

        agency.setVehicles(existingOrNewVehicles);
        Agency createdAgency = agencyRepository.save(agency);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agency> updateAgency(@PathVariable Long id, @RequestBody Agency updatedAgency) {
        if (!agencyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedAgency.setId(id);
        Location location = updatedAgency.getLocation();
        if (location != null && location.getId() == null) {
            Location savedLocation = locationRepository.save(location);
            updatedAgency.setLocation(savedLocation);
        }
        Agency savedAgency = agencyRepository.save(updatedAgency);
        return ResponseEntity.ok(savedAgency);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable Long id) {
        if (!agencyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        agencyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/withVehicles")
    public List<Agency> getAgenciesWithVehicles() {
        return agencyRepository.findAgenciesWithVehicles();
    }

    @GetMapping("/vehiclesMoreThan/{numberOfVehicles}")
    public List<Agency> getAgenciesWithVehiclesMoreThan(@PathVariable("numberOfVehicles") int numberOfVehicles) {
        return agencyRepository.findAgenciesWithVehiclesMoreThan(numberOfVehicles);
    }

    @GetMapping("/country/{country}/vehiclesMoreThan/{numberOfVehicles}")
    public List<Agency> getAgenciesWithCountryAndVehiclesMoreThan(@PathVariable("country") String country, @PathVariable("numberOfVehicles") int numberOfVehicles) {
        return agencyRepository.findAgenciesWithCountryAndVehiclesMoreThan(country, numberOfVehicles);
    }

    @GetMapping("/city/{city}/vehiclesMoreThan/{numberOfVehicles}")
    public List<Agency> getAgenciesWithCityAndVehiclesMoreThan(@PathVariable("city") String city, @PathVariable("numberOfVehicles") int numberOfVehicles) {
        return agencyRepository.findAgenciesWithCityAndVehiclesMoreThan(city, numberOfVehicles);
    }

    @GetMapping("/country/{country}/hasVehicleManufacturer/{vehicleManufacturer}")
    public List<Agency> getAgenciesWithCityAndVehiclesMoreThan(@PathVariable("country") String country, @PathVariable("vehicleManufacturer") String vehicleManufacturer) {
        return agencyRepository.findAgenciesWithCountryAndVehicleManufacturer(country, vehicleManufacturer);
    }

    @GetMapping("/{agencyId}/vehicles")
    public List<Vehicle> getVehiclesByAgency(@PathVariable Long agencyId) {
        return agencyRepository.getVehiclesByAgency(agencyId);
    }
}
