package com.grupo12.multievents.models.dtos;

import com.grupo12.multievents.models.entities.Privilege;
import com.grupo12.multievents.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class AllUserAccessDTO {
    private List<User> users;
    private List<Privilege> privileges;
}
