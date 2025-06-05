package com.example.demoexternalservice.modules.elasticsearch.web.controller;

import com.example.demoexternalservice.modules.elasticsearch.model.Product;
import com.example.demoexternalservice.modules.elasticsearch.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Resource
    private ProductService productService;


    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable String id) {
        return productService.getProductById(id);
    }

}
