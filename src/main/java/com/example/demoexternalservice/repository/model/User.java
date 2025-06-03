package com.example.demoexternalservice.repository.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    private  Long id;
    private String name;
    private String email;
}

