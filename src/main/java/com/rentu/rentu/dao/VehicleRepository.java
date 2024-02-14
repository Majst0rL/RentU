package com.rentu.rentu.dao;

import com.rentu.rentu.models.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    // You can add custom query methods here if needed
}
