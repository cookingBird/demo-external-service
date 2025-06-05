package com.example.demoexternalservice.modules.elasticsearch.service;

import com.example.demoexternalservice.modules.elasticsearch.model.Dynamic;
import com.example.demoexternalservice.modules.elasticsearch.repository.DynamicRepository;
import com.example.demoexternalservice.utils.IteratorUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DynamicService {
    private final DynamicRepository dynamicRepository;

    public DynamicService(DynamicRepository dynamicRepository) {
        this.dynamicRepository = dynamicRepository;
    }

    public Dynamic save(Dynamic dynamic) {
        return dynamicRepository.save(dynamic);
   }

   public Optional<Dynamic> findById(String id) {
        return dynamicRepository.findById(id);
   }

   public List<Dynamic> findAll() {
        return IteratorUtils.collect(dynamicRepository.findAll());
   }
}
