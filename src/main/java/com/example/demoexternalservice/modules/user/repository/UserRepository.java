package com.example.demoexternalservice.modules.user.repository;

import com.example.demoexternalservice.modules.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
