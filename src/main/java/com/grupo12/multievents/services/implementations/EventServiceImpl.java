package com.grupo12.multievents.services.implementations;

import com.grupo12.multievents.models.dtos.SaveEventDTO;
import com.grupo12.multievents.models.dtos.UpdateEventVisibilityDTO;
import com.grupo12.multievents.models.entities.Category;
import com.grupo12.multievents.models.entities.Event;
import com.grupo12.multievents.repositories.EventRepository;
import com.grupo12.multievents.services.EventService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveEvent(SaveEventDTO data, Category category) throws Exception {
        Event newEvent = new Event(data.getTitle(), data.getImage(), data.getDate(), data.getDuration(), category, data.getLocation());
        eventRepository.save(newEvent);
    }

    @Override
    public Event findEventByTitle(String name) {
        return eventRepository.findByTitle(name);
    }

    @Override
    public Event findEventById(String id) {
        UUID code = UUID.fromString(id);
        return eventRepository.findById(code).orElse(null);
    }

    @Override
    public List<Event> findAllEvents() {

        return eventRepository.findAll();
    }
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateVisibility(Event event, UpdateEventVisibilityDTO data) throws Exception {
        event.setVisibility(data.getVisibility());
        eventRepository.save(event);
    }


}
