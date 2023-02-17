package com.example.shoppinglist.model.entity;

import com.example.shoppinglist.model.enums.ProductTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class CategoryEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private ProductTypeEnum name;

    private String description;
    public CategoryEntity(ProductTypeEnum name) {
        this.name = name;
    }
}
