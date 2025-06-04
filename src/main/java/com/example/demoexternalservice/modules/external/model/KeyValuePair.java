package com.example.demoexternalservice.modules.external.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "external_pair")
@Entity
@Data
public class KeyValuePair {
    @Id
    private Long id;

    private  String rowId;

    private String key;

    private String value;

    private String tableName;
}
