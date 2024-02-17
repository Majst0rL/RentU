package com.rentu.rentu.controllers;

import com.rentu.rentu.models.Post;
import com.rentu.rentu.dao.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = (List<Post>) postRepository.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/createPost")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postRepository.save(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setVehicle(postDetails.getVehicle());
        post.setDescription(postDetails.getDescription());
        post.setAgency(postDetails.getAgency());
        Post updatedPost = postRepository.save(post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/findPostsByModelPriceCityAndAgency")
    public ResponseEntity<List<Post>> findPostsByModelPriceCityAndAgency(@RequestParam String model, @RequestParam double pricePerDay, @RequestParam String city) {
        List<Post> posts = postRepository.findPostsByModelPriceCityAndAgency(model, pricePerDay, city);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/findPostsByParameters")
    public ResponseEntity<List<Post>> findPostsByParameters(@RequestParam(required = false) String model, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String city, @RequestParam(required = false) String vehicleType, @RequestParam(required = false) String fuelType, @RequestParam(required = false) Integer year, @RequestParam(required = false) String manufacturer, @RequestParam(required = false) Integer power) {
        List<Post> posts = postRepository.findPostsByParameters(model, minPrice, maxPrice, city, vehicleType, fuelType, year, manufacturer, power);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/findPostsByManufacturer")
    public ResponseEntity<List<Post>> findPostsByManufacturer(@RequestParam String manufacturer) {
        List<Post> posts = postRepository.findPostsByManufacturer(manufacturer);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/findPostsByAgencyLocation")
    public ResponseEntity<List<Post>> findPostsByAgencyLocation(@RequestParam String city) {
        List<Post> posts = postRepository.findPostsByAgencyLocation(city);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/findPostsByVehicleParameters")
    public ResponseEntity<List<Post>> findPostsByVehicleParameters(@RequestParam(required = false) String vehicleType, @RequestParam(required = false) Integer power, @RequestParam(required = false) String manufacturer) {
        List<Post> posts = postRepository.findPostsByVehicleParameters(vehicleType, power, manufacturer);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


}

