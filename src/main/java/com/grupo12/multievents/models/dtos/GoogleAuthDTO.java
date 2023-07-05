package com.grupo12.multievents.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleAuthDTO {
    @NotEmpty(message = "IdToken must not be blank")
    private String idToken;
}
