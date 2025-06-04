package com.example.demoexternalservice.modules.external.web.controller;

import com.example.demoexternalservice.modules.external.service.ExternalService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/external")
public class ExternalController {
    private final ExternalService externalService;

    public ExternalController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @PostMapping("/")
    public void saveExternal(@RequestParam String tableName, @RequestParam String rowId, @RequestBody Map<String, String> payload) {
        externalService.saveExternal(tableName, rowId, payload);
    }

    @GetMapping("/{tableName}/{rowId}")
    public Map<String,String> getExternal(@PathVariable String tableName, @PathVariable String rowId) {
       return externalService.getExternal(tableName, rowId);
    }
}
