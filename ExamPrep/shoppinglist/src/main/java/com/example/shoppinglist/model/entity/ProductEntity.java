package com.example.shoppinglist.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends  BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private BigDecimal price;

    private LocalDateTime neededBefore;

    @ManyToOne
    private CategoryEntity category;

}
