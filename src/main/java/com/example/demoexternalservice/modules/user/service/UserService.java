package com.example.demoexternalservice.modules.user.service;

import com.example.demoexternalservice.modules.user.model.User;

import java.util.Optional;

public interface UserService<K, T> {

    T save(T payload);


    Optional<T> findById(K id);
}
