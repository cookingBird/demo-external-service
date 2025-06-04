package com.example.demoexternalservice.modules.external.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "external_pair")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyValuePair {
    @Id
    private Long id;

    private String rowId;

    private String key;

    private String value;

    private String tableName;
}
