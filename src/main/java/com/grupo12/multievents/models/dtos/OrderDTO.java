package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Order;
import com.grupo12.multievents.models.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private String idOrder;
    private float total;
    private Date date;
    private List<Ticket> tickets;
}
