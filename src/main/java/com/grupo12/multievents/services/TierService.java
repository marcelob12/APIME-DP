package com.grupo12.multievents.services;

import com.grupo12.multievents.models.dtos.SaveTierDTO;
import com.grupo12.multievents.models.dtos.UpdateEventVisibilityDTO;
import com.grupo12.multievents.models.dtos.UpdateTierDTO;
import com.grupo12.multievents.models.entities.Event;
import com.grupo12.multievents.models.entities.Tier;

import java.util.List;

public interface TierService {

    void saveTier(SaveTierDTO data, Event event) throws Exception;

    Tier findTierById(String id);


    List<Tier> findAllTiers();

    void updateVisibility(Tier tier, UpdateEventVisibilityDTO data) throws Exception;

    void updateTier(Tier tier, UpdateTierDTO data) throws Exception;
}