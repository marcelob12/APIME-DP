package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Order;
import com.grupo12.multievents.models.entities.Tier;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveTicketDTO {
    @NotEmpty(message = "Tier must be added")
    private String tier;
    @NotEmpty(message = "Order must be added")
    private String  order;
}
