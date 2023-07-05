package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Privilege;
import com.grupo12.multievents.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserAccessDTO {
    private User user;
    private List<Privilege> privileges;
}
