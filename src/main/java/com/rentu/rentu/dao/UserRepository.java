package com.rentu.rentu.dao;

import com.rentu.rentu.models.User;
import com.rentu.rentu.models.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User login(@Param("username") String username, @Param("password") String password);

    @Query("SELECT u FROM User u JOIN u.location l WHERE l.city = :city")
    List<User> findUsersByCity(@Param("city") String city);
}
