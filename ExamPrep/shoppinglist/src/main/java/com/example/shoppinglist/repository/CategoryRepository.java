package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.entity.CategoryEntity;
import com.example.shoppinglist.model.enums.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(ProductTypeEnum productType);
}
