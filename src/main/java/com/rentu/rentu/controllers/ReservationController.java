package com.rentu.rentu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rentu.rentu.models.Reservation;
import com.rentu.rentu.dao.ReservationRepository;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("/createReservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        reservation.setPrice(reservation.calculatePrice());
        Reservation createdReservation = reservationRepository.save(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }


    @PutMapping("/updateReservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setStartDate(reservationDetails.getStartDate());
        reservation.setEndDate(reservationDetails.getEndDate());
        reservation.setUser(reservationDetails.getUser());
        reservation.setVehicle(reservationDetails.getVehicle());
        reservation.setPrice(reservation.calculatePrice());
        Reservation updatedReservation = reservationRepository.save(reservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
