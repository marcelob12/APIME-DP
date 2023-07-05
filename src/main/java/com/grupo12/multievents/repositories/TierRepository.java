package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Tier;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface TierRepository extends ListCrudRepository<Tier, UUID> {

}
