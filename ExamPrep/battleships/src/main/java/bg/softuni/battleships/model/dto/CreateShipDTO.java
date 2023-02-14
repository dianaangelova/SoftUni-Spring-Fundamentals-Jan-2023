package bg.softuni.battleships.model.dto;

import bg.softuni.battleships.model.entity.CategoryEntity;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateShipDTO {

    @NotBlank
    @Size(min=2, max=10)
    private String name;

    @NotBlank
    @Positive
    private long health;

    @NotBlank
    @Positive
    private long power;

    @NotBlank
    @PastOrPresent
    private LocalDate created;

    @NotBlank
    private CategoryEntity category;
}
