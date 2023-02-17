package bg.softuni.likebook.model.dto;

import bg.softuni.likebook.model.entity.MoodEntity;
import bg.softuni.likebook.model.entity.UserEntity;
import bg.softuni.likebook.model.enums.MoodTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostDTO {

    @NotBlank
    @Size(min = 2, max = 150)
    private String content;

    @NotBlank
    private String mood;

}
