package com.example.shoppinglist.model.entity;

import com.example.shoppinglist.model.enums.ProductTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private ProductTypeEnum name;

    private String description;
    public CategoryEntity(ProductTypeEnum name) {
        this.name = name;
    }
}
