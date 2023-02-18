package com.example.shoppinglist.model.dto;

import com.example.shoppinglist.model.entity.CategoryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateProductDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 5)
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime before;

    @Positive
    private BigDecimal price;

    @NotBlank
    private String category;
}
