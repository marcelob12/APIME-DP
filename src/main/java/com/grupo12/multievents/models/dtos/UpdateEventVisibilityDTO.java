package com.grupo12.multievents.models.dtos;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateEventVisibilityDTO {

    @NotEmpty (message = "Definir una visibilidad al evento")
    private Boolean visibility;
    private UUID id;

}
