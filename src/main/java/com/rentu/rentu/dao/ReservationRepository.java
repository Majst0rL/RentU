package com.rentu.rentu.dao;
import org.springframework.data.repository.CrudRepository;
import com.rentu.rentu.models.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rentu.rentu.models.User;
import com.rentu.rentu.models.Vehicle;
import java.time.LocalDate;
import java.util.List;
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE (:startDate is null or r.startDate = :startDate) and (:endDate is null or r.endDate = :endDate) and (:price is null or r.price = :price) and (:user is null or r.user = :user) and (:vehicle is null or r.vehicle = :vehicle)")
    List<Reservation> findReservations(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("price") Double price, @Param("user") User user, @Param("vehicle") Vehicle vehicle);

}

