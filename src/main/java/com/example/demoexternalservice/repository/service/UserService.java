package com.example.demoexternalservice.repository.service;

import com.example.demoexternalservice.repository.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<User, Long> {
}
