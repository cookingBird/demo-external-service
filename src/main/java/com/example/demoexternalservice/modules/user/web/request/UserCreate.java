package com.example.demoexternalservice.modules.user.web.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;


@Data
public class UserCreate {
    private String name;
    private String email;
    private Map<String,String> rest;
}
