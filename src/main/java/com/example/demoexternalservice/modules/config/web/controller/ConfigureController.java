package com.example.demoexternalservice.modules.config.web.controller;

import com.example.demoexternalservice.modules.config.model.Configure;
import com.example.demoexternalservice.modules.config.service.ConfigService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/external/configs")
public class ConfigureController {
    @Resource
    private ConfigService configService;

    @PostMapping
    public Configure save(@RequestBody Configure configure) {
        return configService.saveConfigure(configure);
    }

    @GetMapping
    public List<Configure> queryByTable(@RequestParam("tableName") String tableName) {
        return configService.getTableConfig(tableName);
    }


    @PutMapping("/{id}")
    public Configure update(@PathVariable("id") Long id, @RequestBody Configure configure) {
        if(!Objects.equals(configure.getId(), id)) throw new RuntimeException();
        return configService.updateConfigure(id, configure);
    }

    @PutMapping("/{id}/patch")
    public Configure patch(@PathVariable("id") Long id, @RequestBody Configure configure) {
        return configService.patchConfigure(id, configure);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        configService.deleteById(id);
    }
}
