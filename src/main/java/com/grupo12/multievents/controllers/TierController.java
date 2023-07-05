package com.grupo12.multievents.controllers;

import com.grupo12.multievents.models.dtos.MessageDTO;
import com.grupo12.multievents.models.dtos.SaveTierDTO;
import com.grupo12.multievents.models.dtos.UpdateEventVisibilityDTO;
import com.grupo12.multievents.models.dtos.UpdateTierDTO;
import com.grupo12.multievents.models.entities.Category;
import com.grupo12.multievents.models.entities.Event;
import com.grupo12.multievents.models.entities.Tier;
import com.grupo12.multievents.services.EventService;
import com.grupo12.multievents.services.TierService;
import com.grupo12.multievents.utils.RequestErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/tier")
public class TierController {

    @Autowired
    RequestErrorHandler requestErrorHandler;

    @Autowired
    TierService tierService;

    @Autowired
    EventService eventService;

    @PostMapping("/")
    public ResponseEntity<?> SaveTier(@RequestBody SaveTierDTO info, BindingResult validations) {
        if (validations.hasErrors())
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);

        Event event = eventService.findEventById(info.getEvent());


        if (event == null)
            return new ResponseEntity<>(new MessageDTO("Evento no encontrada"), HttpStatus.NOT_FOUND);

        List <Tier> tiers = event.getTiers()
                .stream()
                .filter(tier -> tier.getName().equals(info.getName()))
                .collect(Collectors.toList());

        if (!tiers.isEmpty())
            return new ResponseEntity<>(new MessageDTO("Tier existente"), HttpStatus.NOT_FOUND);

        try {
            tierService.saveTier(info, event);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new MessageDTO("El Tier se añadió exitosamente"), HttpStatus.OK);
    }
    @PutMapping("/deleteById/")
    public ResponseEntity<?> updateVisibility(@RequestBody UpdateEventVisibilityDTO data, BindingResult validations) {
        Tier tier = tierService.findTierById(data.getId().toString());
        System.out.println("BOOM" + tier);

        if (validations.hasErrors()) {
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        if (tier == null) {
            return new ResponseEntity<>(new MessageDTO("Tier no encontrado"), HttpStatus.BAD_REQUEST);
        }
        try {
            tierService.updateVisibility(tier, data);
            return new ResponseEntity<>(new MessageDTO("Visibilidad Actualizada"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateTier/")
    public ResponseEntity<?> updateTier(@RequestBody UpdateTierDTO data, BindingResult validations){
        System.out.println("Inicio de Update");
        Tier tier = tierService.findTierById(data.getId().toString());
        System.out.println("Acaaaaaa"+ tier);

        if (validations.hasErrors()) {
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        if (tier == null) {
            return new ResponseEntity<>(new MessageDTO("Tier no encontrado"), HttpStatus.BAD_REQUEST);
        }
        try {
            tierService.updateTier(tier, data);
            return new ResponseEntity<>(new MessageDTO("Tier Actualizada"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTierId(@PathVariable("id") String id) {

        Tier tier = tierService.findTierById(id);

        if (tier == null) {
            return new ResponseEntity<>(new MessageDTO("El id deL tier es inválido"), HttpStatus.NOT_FOUND);
        }

        try {
            System.out.println("tier:" + tier);
            return new ResponseEntity<>(tier, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllTier() {

        return new ResponseEntity<>(tierService.findAllTiers(), HttpStatus.OK);
    }

}

