package com.example.demoexternalservice.modules.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private Double price;
    private String category;

}
