package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Privilege;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface PrivilegeRepository extends ListCrudRepository<Privilege, UUID> {

    Privilege findByName(String name);

}
