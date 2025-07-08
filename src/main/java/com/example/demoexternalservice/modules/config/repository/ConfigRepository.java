package com.example.demoexternalservice.modules.config.repository;

import com.example.demoexternalservice.modules.config.model.Configure;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigRepository extends JpaRepository<Configure,Long> {

    List<Configure> findByTableName(String tableName);

    List<Configure> findByTableNameAndVersion(String tableName, String fieldVersion, Pageable page);
}
