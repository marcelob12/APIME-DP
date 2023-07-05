package com.grupo12.multievents.services;

import com.grupo12.multievents.models.dtos.SaveAccessDTO;
import com.grupo12.multievents.models.entities.Access;
import com.grupo12.multievents.models.entities.Privilege;
import com.grupo12.multievents.models.entities.User;

import java.util.List;
import java.util.UUID;

public interface AccessService {
    void saveAccess(User user, Privilege privilege) throws Exception;

    List<Access> findAccessByUser(UUID id);

    Access findAccessById(String id);

    List<Access> findAllAccess();

    void deleteAccessById(String id) throws Exception;
}
