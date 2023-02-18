package com.example.resellerapp.model.entity;

import com.example.resellerapp.model.enums.ConditionTypeEnum;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "conditions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConditionEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private ConditionTypeEnum name;

    @Column(nullable = false)
    private String description;
    public ConditionEntity(ConditionTypeEnum name) {
        this.name = name;
        this.description= name.description;
    }
}
