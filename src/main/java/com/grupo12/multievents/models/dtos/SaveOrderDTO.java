package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SaveOrderDTO {

    @NotEmpty(message = "total must be calculated ")
    private float total;

    @NotEmpty(message = "date must be established")
    private Date date;

    @NotEmpty(message = "User must be established")
    private String identifier;

}
