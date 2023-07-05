package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Privilege;
import com.grupo12.multievents.models.entities.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveAccessDTO {
    @NotEmpty(message = "User must be added")
    private String identifier;

    @NotEmpty(message = "Privilege must be added")
    private String privilegeName;
}
