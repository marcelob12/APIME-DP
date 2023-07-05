package com.grupo12.multievents.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SaveEventDTO {
    @NotEmpty(message = "Title must not be blank")
    private String title;

    @NotEmpty(message = "Image must not be blank")
    private String image;

    @NotEmpty(message = "Location must not be blank")
    private String location;

    @NotEmpty(message = "Day of the event must not be blank")
    private Date date;

    @NotEmpty(message = "Duration must not be blank")
    private int duration;

    @NotEmpty(message = "Category must not be blank")
    private String category;

}
