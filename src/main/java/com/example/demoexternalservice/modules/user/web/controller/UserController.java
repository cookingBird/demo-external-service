package com.example.demoexternalservice.modules.user.web.controller;

import com.example.demoexternalservice.modules.user.model.User;
import com.example.demoexternalservice.modules.user.service.UserServiceImpl;
import com.example.demoexternalservice.modules.user.web.request.UserCreate;
import com.example.demoexternalservice.modules.user.web.response.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findAllById(id);
    }

    @PostMapping("/")
    public Long saveUser(@RequestBody UserCreate userCreate) {
        return userService.save(
                User.builder()
                        .name(userCreate.getName())
                        .email(userCreate.getEmail())
                        .build(),
                userCreate.getRest()
        );
    }
}
