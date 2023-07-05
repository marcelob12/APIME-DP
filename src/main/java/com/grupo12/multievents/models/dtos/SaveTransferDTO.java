package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Ticket;
import com.grupo12.multievents.models.entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public class SaveTransferDTO {
    @NotEmpty(message = "init date must be established")
    private Date initDate;
    @NotEmpty(message = "transfer date must be established")
    private Date tranferDate;
    @NotEmpty(message = "Must be completed")
    private User userFrom;
    @NotEmpty(message = "Must be completed")
    private User userTo;
    @NotEmpty(message = "Ticket must be added")
    private Ticket ticket;


}
