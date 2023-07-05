package com.grupo12.multievents.services;

import com.grupo12.multievents.models.entities.Privilege;

import java.util.List;
import java.util.UUID;

public interface PrivilegeService {
    void savePrivilege() throws Exception;

    Privilege findPrivilegeById(UUID id);

    Privilege findPrivilegeByName(String name);

    List<Privilege> findAllPrivileges();

    void deletePrivilegeById(String id) throws Exception;


}
