package com.example.demoexternalservice.modules.elasticsearch.service;

import com.example.demoexternalservice.modules.elasticsearch.model.Product;
import com.example.demoexternalservice.modules.elasticsearch.repository.ProductRepository;
import com.example.demoexternalservice.utils.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
      return productRepository.save(product);
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> getAll() {
        return IteratorUtils.collect(productRepository.findAll());
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
