package com.rentu.rentu.controllers;

import com.rentu.rentu.dao.AgencyRepository;
import com.rentu.rentu.dao.VehicleRepository;
import com.rentu.rentu.models.Agency;
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

    @Autowired
    public AgencyController(AgencyRepository agencyRepository, VehicleRepository vehicleRepository) {
        this.agencyRepository = agencyRepository;
        this.vehicleRepository = vehicleRepository;
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
        List<Vehicle> existingOrNewVehicles = new ArrayList<>();

        for (Vehicle vehicle : agency.getVehicles()) {
            Optional<Vehicle> optionalExistingVehicle = vehicleRepository.findById(vehicle.getId());

            if (optionalExistingVehicle.isPresent()) {
                existingOrNewVehicles.add(optionalExistingVehicle.get());
            } else {
                // Return a response indicating that the vehicle with the specified ID is not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vehicle with ID " + vehicle.getId() + " is not in the database yet.");
            }
        }

        // Set the existing or new vehicles to the agency
        agency.setVehicles(existingOrNewVehicles);

        // Save the agency
        Agency createdAgency = agencyRepository.save(agency);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agency> updateAgency(@PathVariable Long id, @RequestBody Agency updatedAgency) {
        if (!agencyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedAgency.setId(id);
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
}
