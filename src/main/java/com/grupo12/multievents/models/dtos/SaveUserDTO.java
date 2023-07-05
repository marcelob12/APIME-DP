package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Avatar;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SaveUserDTO {

    @NotEmpty(message = "Email must not be blank")
    private String email;

    @NotEmpty(message = "Name must not be blank")
    private String username;

    @NotEmpty(message = "Password must not be blank")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;

    @NotEmpty(message = "Avatar must not be blank")
    private String avatar;
}
