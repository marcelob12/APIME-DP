package com.grupo12.multievents.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class SaveCategoryDTO {
    @NotEmpty(message = "name must be added")
    private String name;
}
