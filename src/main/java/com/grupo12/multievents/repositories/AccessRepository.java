package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Access;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface AccessRepository extends ListCrudRepository<Access, UUID> {
    List<Access> findByUserIdUser(UUID id);
}
