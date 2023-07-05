package com.grupo12.multievents.services;

import com.grupo12.multievents.models.dtos.SaveEventDTO;
import com.grupo12.multievents.models.dtos.UpdateEventVisibilityDTO;
import com.grupo12.multievents.models.entities.Category;
import com.grupo12.multievents.models.entities.Event;
import com.grupo12.multievents.models.entities.Tier;

import java.util.List;

public interface EventService {
    void saveEvent(SaveEventDTO data, Category category) throws Exception;

    Event findEventById(String id);

    Event findEventByTitle(String name);

    List<Event> findAllEvents();

    void updateVisibility(Event event, UpdateEventVisibilityDTO data) throws Exception;


}
