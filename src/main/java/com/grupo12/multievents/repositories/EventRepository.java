package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Event;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface EventRepository extends ListCrudRepository<Event, UUID> {
    Event findByTitle(String name);
}
