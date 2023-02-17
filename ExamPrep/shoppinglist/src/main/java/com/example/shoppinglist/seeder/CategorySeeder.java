package com.example.shoppinglist.seeder;

import com.example.shoppinglist.model.entity.CategoryEntity;
import com.example.shoppinglist.model.enums.ProductTypeEnum;
import com.example.shoppinglist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            List<CategoryEntity> categories = Arrays.stream(ProductTypeEnum.values())
                    .map(name -> new CategoryEntity(name))
                    .collect(Collectors.toList());

            this.categoryRepository.saveAll(categories);

        }
    }
}

