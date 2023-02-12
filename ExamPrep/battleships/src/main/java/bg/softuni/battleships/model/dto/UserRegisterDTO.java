package bg.softuni.battleships.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {

    @NotBlank
    @Size(min = 3, max = 10)
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String fullName;

    @Email
    private String email;

    @NotBlank
    @Size(min=3)
    private String password;

    @NotBlank
    @Size(min=3)
    private String confirmPassword;
}
