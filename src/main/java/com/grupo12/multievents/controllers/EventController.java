package com.grupo12.multievents.controllers;

import com.grupo12.multievents.models.dtos.MessageDTO;

import com.grupo12.multievents.models.dtos.SaveEventDTO;
import com.grupo12.multievents.models.dtos.UpdateEventVisibilityDTO;
import com.grupo12.multievents.models.entities.Category;
import com.grupo12.multievents.models.entities.Event;
import com.grupo12.multievents.services.CategoryService;
import com.grupo12.multievents.services.EventService;
import com.grupo12.multievents.utils.RequestErrorHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/event")
public class EventController {

    @Autowired
    RequestErrorHandler requestErrorHandler;

    @Autowired
    EventService eventService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllEvents() {

        List<Event> eventList = eventService.findAllEvents();
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEventById(@PathVariable("id") String id) {

        Event event = eventService.findEventById(id);

        if (event == null) {
            return new ResponseEntity<>(new MessageDTO("El id deL evento es inválido"), HttpStatus.NOT_FOUND);
        }

        try {

            return new ResponseEntity<>(event, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deleteById/")
    public ResponseEntity<?> updateVisibility(@RequestBody UpdateEventVisibilityDTO data, BindingResult validations) {
        Event event = eventService.findEventById(data.getId().toString());

        if (validations.hasErrors()) {
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        if (event == null) {
            return new ResponseEntity<>(new MessageDTO("Evento no encontrado"), HttpStatus.BAD_REQUEST);
        }
        try {
            eventService.updateVisibility(event, data);
            return new ResponseEntity<>(new MessageDTO("Visibilidad Actualizada"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/")
    public ResponseEntity<?> saveEvent(@RequestBody SaveEventDTO info, BindingResult validations) {
        if (validations.hasErrors())
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);

        Event event = eventService.findEventByTitle(info.getTitle());

        System.out.println(event);

        if (event != null)
            return new ResponseEntity<>(new MessageDTO("Este evento ya existe"), HttpStatus.BAD_REQUEST);

        Category category = categoryService.findCategoryByName(info.getCategory());

        if (category == null)
            return new ResponseEntity<>(new MessageDTO("Categoría no encontrada"), HttpStatus.NOT_FOUND);

        try {
            eventService.saveEvent(info, category);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new MessageDTO("El evento se añadió exitosamente"), HttpStatus.OK);


    }

}
