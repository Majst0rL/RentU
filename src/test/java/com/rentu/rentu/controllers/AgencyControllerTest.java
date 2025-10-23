package com.rentu.rentu.controllers;

import com.rentu.rentu.dao.AgencyRepository;
import com.rentu.rentu.dao.LocationRepository;
import com.rentu.rentu.dao.VehicleRepository;
import com.rentu.rentu.models.Agency;
import com.rentu.rentu.models.Location;
import com.rentu.rentu.models.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgencyControllerTest {

    @Mock
    private AgencyRepository agencyRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private AgencyController agencyController;

    private Agency agency;
    private Location location;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        location = new Location();
        location.setId(1L);
        location.setCity("Ljubljana");
        location.setCountry("Slovenia");

        vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setManufacturer("Audi");

        agency = new Agency();
        agency.setId(1L);
        agency.setName("Test Agency");
        agency.setLocation(location);
        agency.setVehicles(List.of(vehicle));
    }


    @Test
    void testGetAgencyById_Positive() {
        when(agencyRepository.findById(1L)).thenReturn(Optional.of(agency));

        ResponseEntity<Agency> response = agencyController.getAgencyById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Agency", response.getBody().getName());
    }

    @Test
    void testGetAgencyById_Boundary() {
        when(agencyRepository.findById(0L)).thenReturn(Optional.empty());

        ResponseEntity<Agency> response = agencyController.getAgencyById(0L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAgencyById_Negative() {
        when(agencyRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Agency> response = agencyController.getAgencyById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }



    @Test
    void testCreateAgency_Positive() {
        when(locationRepository.save(any(Location.class))).thenReturn(location);
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(agencyRepository.save(any(Agency.class))).thenReturn(agency);

        ResponseEntity<?> response = agencyController.createAgency(agency);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertInstanceOf(Agency.class, response.getBody());
    }

    @Test
    void testCreateAgency_Boundary_EmptyVehicles() {
        Agency newAgency = new Agency();
        newAgency.setLocation(location);
        newAgency.setVehicles(new ArrayList<>());

        when(agencyRepository.save(any(Agency.class))).thenReturn(newAgency);

        ResponseEntity<?> response = agencyController.createAgency(newAgency);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertInstanceOf(Agency.class, response.getBody());
    }

    @Test
    void testCreateAgency_Negative_VehicleNotFound() {
        Vehicle unknownVehicle = new Vehicle();
        unknownVehicle.setId(99L);
        Agency badAgency = new Agency();
        badAgency.setVehicles(List.of(unknownVehicle));

        when(vehicleRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = agencyController.createAgency(badAgency);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Vehicle with ID 99"));
    }


    @Test
    void testUpdateAgency_Positive() {
        when(agencyRepository.existsById(1L)).thenReturn(true);
        when(locationRepository.save(any(Location.class))).thenReturn(location);
        when(agencyRepository.save(any(Agency.class))).thenReturn(agency);

        ResponseEntity<Agency> response = agencyController.updateAgency(1L, agency);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateAgency_Boundary_NoLocation() {
        Agency agencyNoLocation = new Agency();
        agencyNoLocation.setId(1L);
        agencyNoLocation.setName("No Location");

        when(agencyRepository.existsById(1L)).thenReturn(true);
        when(agencyRepository.save(any(Agency.class))).thenReturn(agencyNoLocation);

        ResponseEntity<Agency> response = agencyController.updateAgency(1L, agencyNoLocation);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateAgency_Negative_NotFound() {
        when(agencyRepository.existsById(2L)).thenReturn(false);

        ResponseEntity<Agency> response = agencyController.updateAgency(2L, agency);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }



    @Test
    void testDeleteAgency_Positive() {
        when(agencyRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = agencyController.deleteAgency(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(agencyRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAgency_Negative_NotFound() {
        when(agencyRepository.existsById(99L)).thenReturn(false);

        ResponseEntity<Void> response = agencyController.deleteAgency(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(agencyRepository, never()).deleteById(any());
    }

    @Test
    void testGetAgenciesWithVehiclesMoreThan_Positive() {
        when(agencyRepository.findAgenciesWithVehiclesMoreThan(2))
                .thenReturn(List.of(agency));

        List<Agency> result = agencyController.getAgenciesWithVehiclesMoreThan(2);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetAgenciesWithVehiclesMoreThan_Boundary_Zero() {
        when(agencyRepository.findAgenciesWithVehiclesMoreThan(0))
                .thenReturn(Collections.emptyList());

        List<Agency> result = agencyController.getAgenciesWithVehiclesMoreThan(0);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAgenciesWithVehiclesMoreThan_Negative_InvalidNumber() {
        when(agencyRepository.findAgenciesWithVehiclesMoreThan(-1))
                .thenReturn(Collections.emptyList());

        List<Agency> result = agencyController.getAgenciesWithVehiclesMoreThan(-1);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
