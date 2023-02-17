package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.SongEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {


    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min=3, max = 20)
    private String password;

    @NotBlank
    @Size(min=3, max=20)
    private String confirmPassword;




}
