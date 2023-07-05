package com.grupo12.multievents.models.dtos;

import jakarta.validation.constraints.NotEmpty;

public class SavePrivilegeDTO {
    @NotEmpty(message = "name must be added")
    private String name;
    @NotEmpty(message = "Route must be added")
    private String route;

}
