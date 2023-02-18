package com.example.shoppinglist.model.dto;

import com.example.shoppinglist.model.entity.ProductEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    public ProductDTO(ProductEntity productEntity) {
        this.id=productEntity.getId();
        this.name=productEntity.getName();
        this.price=productEntity.getPrice();
    }

    @Override
    public String toString() {
        return
                String.format("Name: %s Price: %.2f lv", name, price);
    }
}
