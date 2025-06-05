package com.example.demoexternalservice.modules.elasticsearch.repository;

import com.example.demoexternalservice.modules.elasticsearch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByName(String name);
}
