package com.rentu.rentu.dao;

import com.rentu.rentu.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
