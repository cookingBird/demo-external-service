package com.example.demoexternalservice.modules.config.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "config")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Configure {
    @Id
    @GeneratedValue
    private Long id;

    private String tableName;

    private String name;

    private String type;

    private String version;

    private String fallback;

    private String description;
}
