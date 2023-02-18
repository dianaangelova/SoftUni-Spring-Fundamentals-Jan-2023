package com.example.shoppinglist.service;

import com.example.shoppinglist.model.dto.CreateProductDTO;
import com.example.shoppinglist.model.dto.ProductDTO;
import com.example.shoppinglist.model.entity.CategoryEntity;
import com.example.shoppinglist.model.entity.ProductEntity;
import com.example.shoppinglist.model.enums.ProductTypeEnum;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.repository.UserRepository;
import com.example.shoppinglist.user.CurrentUser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository songRepository, CurrentUser currentUser, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = songRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public boolean create(CreateProductDTO createProductDTO) {

        ProductTypeEnum productType = ProductTypeEnum.valueOf(createProductDTO.getCategory());
        CategoryEntity category = this.categoryRepository.findByName(productType);

        ProductEntity product = new ProductEntity();

        product.setName(createProductDTO.getName());
        product.setDescription(createProductDTO.getDescription());
        product.setPrice(createProductDTO.getPrice());
        product.setNeededBefore(createProductDTO.getBefore());

        product.setCategory(category);

        this.productRepository.save(product);
        return true;
    }

    public List<List<ProductDTO>> getAllProductsByCategory() {

        List<List<ProductDTO>> productsByCategory = new ArrayList<>();

        List<ProductDTO> food = this.getProductsByCategory(ProductTypeEnum.FOOD);
        List<ProductDTO> drink = this.getProductsByCategory(ProductTypeEnum.DRINK);
        List<ProductDTO> household = this.getProductsByCategory(ProductTypeEnum.HOUSEHOLD);
        List<ProductDTO> other = this.getProductsByCategory(ProductTypeEnum.OTHER);

        productsByCategory.add(food);
        productsByCategory.add(drink);
        productsByCategory.add(household);
        productsByCategory.add(other);

        return productsByCategory;
    }

    private List<ProductDTO> getProductsByCategory(ProductTypeEnum productType) {

        CategoryEntity category = categoryRepository.findByName(productType);

        return this.productRepository.findAllByCategory(category)
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalPriceOfProducts() {

        List<ProductEntity> allProducts = this.productRepository.findAll();

        BigDecimal totalPrice = BigDecimal.valueOf(0);

        for (ProductEntity p : allProducts) {

            totalPrice = totalPrice.add(p.getPrice());
        }

        return totalPrice;
    }

    public void buyProductWithID(Long id) {
        ProductEntity product = this.productRepository.findById(id).orElse(null);
        this.productRepository.delete(product);
    }

    public void removeAllProducts() {
        this.productRepository.deleteAll();
    }
}
