package com.rentu.rentu.dao;

import com.rentu.rentu.models.Agency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends CrudRepository<Agency, Long> {
    // You can add custom query methods here if needed
}
