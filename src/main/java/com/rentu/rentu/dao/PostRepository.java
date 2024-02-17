package com.rentu.rentu.dao;

import com.rentu.rentu.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long>{
    //QUERY with 3 models and parameters agency location, model, city
    @Query("SELECT DISTINCT p FROM Post p JOIN FETCH p.vehicle v JOIN FETCH p.agency a JOIN FETCH a.location l WHERE v.model = :model AND v.pricePerDay <= :pricePerDay AND l.city = :city")
    List<Post> findPostsByModelPriceCityAndAgency(@Param("model") String model, @Param("pricePerDay") double pricePerDay, @Param("city") String city);

    //QUERY with 3 models for search by any parameter
    @Query("SELECT p FROM Post p JOIN FETCH p.vehicle v JOIN FETCH p.agency a JOIN FETCH a.location l WHERE " +
            "(:model is null or v.model = :model) and " +
            "(:minPrice is null or v.pricePerDay >= :minPrice) and " +
            "(:maxPrice is null or v.pricePerDay <= :maxPrice) and " +
            "(:city is null or l.city = :city) and " +
            "(:vehicleType is null or v.vehicleType = :vehicleType) and " +
            "(:fuelType is null or v.fuelType = :fuelType) and " +
            "(:year is null or v.year = :year) and " +
            "(:manufacturer is null or v.manufacturer = :manufacturer) and " +
            "(:power is null or v.power = :power)")
    List<Post> findPostsByParameters(@Param("model") String model, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("city") String city, @Param("vehicleType") String vehicleType, @Param("fuelType") String fuelType, @Param("year") Integer year, @Param("manufacturer") String manufacturer, @Param("power") Integer power);

    //Querry with 2 models for searching posts for certain vehicle manufacturer
    @Query("SELECT p FROM Post p JOIN FETCH p.vehicle v WHERE v.manufacturer = :manufacturer")
    List<Post> findPostsByManufacturer(@Param("manufacturer") String manufacturer);

    //Querry with 2 models for searching posts by agency location
    @Query("SELECT p FROM Post p JOIN FETCH p.agency a JOIN FETCH a.location l WHERE l.city = :city")
    List<Post> findPostsByAgencyLocation(@Param("city") String city);

    //Querry with 2 models for searchig posts by Vehicle power or type or manufacturer
    @Query("SELECT p FROM Post p JOIN FETCH p.vehicle v WHERE (:vehicleType is null or v.vehicleType = :vehicleType) and (:power is null or v.power = :power) and (:manufacturer is null or v.manufacturer = :manufacturer)")
    List<Post> findPostsByVehicleParameters(@Param("vehicleType") String vehicleType, @Param("power") Integer power, @Param("manufacturer") String manufacturer);

}
