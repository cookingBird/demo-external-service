package com.example.demoexternalservice.modules.external.repository;

import com.example.demoexternalservice.modules.external.model.KeyValuePair;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeyValueRepository extends CrudRepository<KeyValuePair,Long> {
    List<KeyValuePair> findByRowIdAndTableName(String rowId, String tableName);


}
