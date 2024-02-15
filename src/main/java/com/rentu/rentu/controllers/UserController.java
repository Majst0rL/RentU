package com.rentu.rentu.controllers;

import com.rentu.rentu.dao.UserRepository;
import com.rentu.rentu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
}
