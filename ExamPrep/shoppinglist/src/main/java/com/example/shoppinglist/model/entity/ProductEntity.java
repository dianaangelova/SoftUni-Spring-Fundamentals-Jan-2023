package com.example.shoppinglist.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="products")
public class ProductEntity extends  BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private BigDecimal price;

    private LocalDateTime neededBefore;

    @ManyToOne
    private CategoryEntity category;

}
