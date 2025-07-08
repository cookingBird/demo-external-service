package com.example.demoexternalservice.modules.config.model;

import com.example.demoexternalservice.modules.config.enums.Enable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "config")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Configure extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String modelName;

    private String fieldName;

    private String type;

    private String version;

    private String fallback;

    private String description;

    @Enumerated(EnumType.STRING)
    private Enable enable = Enable.TRUE;
}
