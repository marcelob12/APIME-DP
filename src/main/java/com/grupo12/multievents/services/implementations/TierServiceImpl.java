package com.grupo12.multievents.services.implementations;

import com.grupo12.multievents.models.dtos.SaveTierDTO;
import com.grupo12.multievents.models.dtos.UpdateEventVisibilityDTO;
import com.grupo12.multievents.models.dtos.UpdateTierDTO;
import com.grupo12.multievents.models.entities.Event;
import com.grupo12.multievents.models.entities.Tier;
import com.grupo12.multievents.repositories.TierRepository;
import com.grupo12.multievents.services.TierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class TierServiceImpl implements TierService {
    @Autowired
    TierRepository tierRepository;
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveTier(SaveTierDTO data, Event event) throws Exception {
    double value = data.getPrice();
    int intPrice = (int) value;
        Tier newTier = new Tier(data.getName(),intPrice,data.getCapacity(),event);
        tierRepository.save(newTier);
    }

    @Override
    public Tier findTierById(String id) {
        UUID code = UUID.fromString(id);
                return tierRepository.findById(code).orElse(null);
    }

    @Override
    public List<Tier> findAllTiers() {
        return tierRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateVisibility(Tier tier, UpdateEventVisibilityDTO data) throws Exception {
        tier.setVisibility(data.getVisibility());
        tierRepository.save(tier);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateTier(Tier tier, UpdateTierDTO data) throws Exception {
        System.out.println("BOOOOOOOOM" + data);
        if (data.getName() != null) {
            tier.setName(data.getName());
        }
        if (data.getPrice() >= 0) {
            tier.setPrice(data.getPrice());
        }
        if (data.getCapacity() >= 0) {
            tier.setCapacity(data.getCapacity());
        }
        if (data.getVisibility() != null){
            tier.setVisibility(data.getVisibility());
        }
        tierRepository.save(tier);
    }


}
