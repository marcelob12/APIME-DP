package com.grupo12.multievents.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AvatarDTO {

    @NotEmpty(message = "Url must not be blank...")
    private String url;

}
