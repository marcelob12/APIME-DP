package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Event;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SaveTierDTO {


    @NotEmpty(message = "name must be added")
    private String name;

    @NotNull(message = "capacity must be added")
    private int capacity;

    @NotNull(message = "price must be added")
    private double price;

    @NotEmpty(message = "event must be established")
    private String event;

}
