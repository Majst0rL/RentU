package com.rentu.rentu.dao;
import org.springframework.data.repository.CrudRepository;
import com.rentu.rentu.models.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}

