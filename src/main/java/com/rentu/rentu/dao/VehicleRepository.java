package com.rentu.rentu.dao;

import com.rentu.rentu.models.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE v.pricePerDay < :pricePerDay AND v.year < :year")
    List<Vehicle> findVehicleWithPricePerDayLessThanAndYearLessThan(@Param("pricePerDay") double pricePerDay, @Param("year") int year);

    @Query("SELECT v FROM Vehicle v WHERE v.power > :power AND v.pricePerDay < :pricePerDay AND v.year < :year")
    List<Vehicle> findVehicleWithPowerMoreThanAndPricePerDayLessThanAndYearLessThan(@Param("power") int power, @Param("pricePerDay") double pricePerDay, @Param("year") int year);

    @Query("SELECT v FROM Vehicle v WHERE v.manufacturer = :manufacturer AND v.model = :model AND v.year < :year")
    List<Vehicle> findVehicleWithManufacturerAndModelAndYearLessThan(@Param("manufacturer") String manufacturer, @Param("model") String model, @Param("year") int year);


}
