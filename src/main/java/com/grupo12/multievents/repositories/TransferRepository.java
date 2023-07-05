package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Transfer;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface TransferRepository extends ListCrudRepository<Transfer, UUID> {

}
