package com.example.demoexternalservice.modules.elasticsearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.Map;

@Document(indexName = "dynamic")
@Data
@Builder
public class Dynamic {
    @Id
    private String id;

    @Field(type = FieldType.Object, dynamic = org.springframework.data.elasticsearch.annotations.Dynamic.TRUE)
    private Map<String, Object> data;
}
