package com.rentu.rentu.controllers;

import com.rentu.rentu.dao.LocationRepository;
import com.rentu.rentu.dao.UserRepository;
import com.rentu.rentu.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;
}

