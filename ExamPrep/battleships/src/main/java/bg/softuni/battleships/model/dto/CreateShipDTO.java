package bg.softuni.battleships.model.dto;

import bg.softuni.battleships.model.entity.CategoryEntity;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateShipDTO {

    @NotBlank
    @Size(min=2, max=10)
    private String name;

    @Positive
    @NotNull
    private long health;

    @Positive
    @NotNull
    private long power;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate created;

    @Positive
    @NotNull
    private int category=-1;
}
