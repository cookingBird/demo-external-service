package com.example.demoexternalservice.modules.user.web.controller;

import com.example.demoexternalservice.modules.user.model.User;
import com.example.demoexternalservice.modules.user.repository.UserRepository;
import com.example.demoexternalservice.modules.user.web.request.UserCreate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @PostMapping("/")
    public User saveUser(@RequestBody UserCreate user) {
        return userRepository.save(
                User.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .build()
        );
    }
}
