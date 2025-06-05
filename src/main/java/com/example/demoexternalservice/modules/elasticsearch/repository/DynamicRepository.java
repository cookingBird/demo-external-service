package com.example.demoexternalservice.modules.elasticsearch.repository;

import com.example.demoexternalservice.modules.elasticsearch.model.Dynamic;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DynamicRepository  extends ElasticsearchRepository<Dynamic, String> {
    @Query("{ \"bool\": { \"must\": [ { \"match\": { \"data.name\": \"?0\" } } ] } }")
    List<Dynamic> findByNameInFields(String name);
}
