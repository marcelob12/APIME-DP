package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Ticket;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public class SaveQrCodeDTO {
    @NotEmpty(message = "Date must be established")
    private Date claimedDate;
    @NotEmpty(message = "Date must be established")
    private Date generatedDate;
    @NotEmpty(message = "Date must be established")
    private Date expiredDate;
    @NotEmpty(message = "Ticket must be added")
    private Ticket ticket;

}
