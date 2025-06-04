package com.example.demoexternalservice.modules.external.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "external")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyValuePair {

    @Id
    @GeneratedValue
    private Long id;

    private String rowId;

    private String field;

    private String value;

    private String tableName;
}
