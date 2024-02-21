package com.rentu.rentu.controllers;

import com.rentu.rentu.dao.UserRepository;
import com.rentu.rentu.models.User;
import com.rentu.rentu.models.UserRole;
import com.rentu.rentu.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //GET all users
    @GetMapping
    public List<User> getAllUsers() {return (List<User>) userRepository.findAll();}

    //GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<User> login(@PathVariable("username") String username, @PathVariable("password") String password) {
        User user = userRepository.login(username, password);

        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(user);
    }

    //Create User
    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    //UPDATE User
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedUser.setId(id);
        userRepository.save(updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{id}/userRole")
    public ResponseEntity<User> updateUserRole(@PathVariable("id") Long id, @RequestBody UserRole userRole) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userOptional.get().setUserRole(userRole);
        userRepository.save(userOptional.get());
        return ResponseEntity.ok(userOptional.get());
    }

    //DELETE User
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //SEARCH by name and last name
    @GetMapping("/search")
    public List<User> findUsersByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }


    //Register USER
    //@PostMapping("/register")
    //public ResponseEntity<String> registerUser(@RequestBody User newUser) {
    //    if (userRepository.existsByUsername(newUser.getUsername())) {
    //        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
    //    }
    //    newUser.setUserRole(UserRole.USER);
    //    userRepository.save(newUser);
    //    return ResponseEntity.ok("User registered successfully");
    //}
}
