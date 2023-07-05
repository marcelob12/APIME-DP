package com.grupo12.multievents.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    @NotEmpty(message = "Email must not be blank")
    private String email;

    @NotEmpty(message = "Password must not be blank")
    private String password;
}
