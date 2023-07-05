package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Token;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDTO {
    private String username;
    private String email;
    private String token;

    public LoginResponseDTO(String username, String email, Token token) {
        this.username = username;
        this.email = email;
        this.token = token.getContent();
    }
}

