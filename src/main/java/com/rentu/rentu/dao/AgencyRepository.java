package com.rentu.rentu.dao;

import com.rentu.rentu.models.Agency;
import com.rentu.rentu.models.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface AgencyRepository extends CrudRepository<Agency, Long> {
    @Query("SELECT DISTINCT a FROM Agency a JOIN FETCH a.vehicles v WHERE SIZE(a.vehicles) > 0")
    List<Agency> findAgenciesWithVehicles();

    @Query("SELECT DISTINCT a FROM Agency a JOIN FETCH a.vehicles v WHERE SIZE(a.vehicles) > :numberOfVehicles")
    List<Agency> findAgenciesWithVehiclesMoreThan(@Param("numberOfVehicles") int numberOfVehicles);

    @Query("SELECT DISTINCT a FROM Agency a JOIN FETCH a.location l JOIN FETCH a.vehicles v WHERE l.country = :country AND SIZE(a.vehicles) > :numberOfVehicles")
    List<Agency> findAgenciesWithCountryAndVehiclesMoreThan(@Param("country") String country, @Param("numberOfVehicles") int numberOfVehicles);

    @Query("SELECT DISTINCT a FROM Agency a JOIN FETCH a.location l JOIN FETCH a.vehicles v WHERE l.city = :city AND SIZE(a.vehicles) > :numberOfVehicles")
    List<Agency> findAgenciesWithCityAndVehiclesMoreThan(@Param("city") String city, @Param("numberOfVehicles") int numberOfVehicles);

    @Query("SELECT DISTINCT a FROM Agency a JOIN FETCH a.location l JOIN FETCH a.vehicles v WHERE l.country = :country AND EXISTS (SELECT 1 FROM Vehicle v2 WHERE v2 MEMBER OF a.vehicles AND v2.manufacturer = :vehicleManufacturer)")
    List<Agency> findAgenciesWithCountryAndVehicleManufacturer(@Param("country") String country, @Param("vehicleManufacturer") String vehicleManufacturer);

    @Query("SELECT v FROM Agency a JOIN a.vehicles v WHERE a.id = :agencyId")
    List<Vehicle> getVehiclesByAgency(@Param("agencyId") Long agencyId);
}
