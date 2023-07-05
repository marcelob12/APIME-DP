package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Avatar;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface AvatarRepository extends ListCrudRepository<Avatar, UUID> {
    Avatar findByUrl(String url);
}
