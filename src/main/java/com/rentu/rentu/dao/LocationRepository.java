package com.rentu.rentu.dao;

import com.rentu.rentu.models.Location;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findLocationsByUsers_Username(String username);
}
