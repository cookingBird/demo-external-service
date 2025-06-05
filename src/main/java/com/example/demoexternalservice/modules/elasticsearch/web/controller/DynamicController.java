package com.example.demoexternalservice.modules.elasticsearch.web.controller;

import com.example.demoexternalservice.modules.elasticsearch.model.Dynamic;
import com.example.demoexternalservice.modules.elasticsearch.service.DynamicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dynamic")
public class DynamicController {

    @Resource
    private DynamicService dynamicService;

    @PostMapping
    public Dynamic save(@RequestBody Map<String, Object> payload) {
        return dynamicService.save(Dynamic.builder()
                .data(payload)
                .build()
        );
    }

    @GetMapping("/{id}")
    public Dynamic findById(@PathVariable String id) {
        return dynamicService.findById(id).orElse(null);
    }
}
