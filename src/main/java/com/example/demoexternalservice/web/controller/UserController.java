package com.example.demoexternalservice.web.controller;

import com.example.demoexternalservice.repository.model.User;
import com.example.demoexternalservice.repository.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        return optionalUser.orElse(null);
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }
}
