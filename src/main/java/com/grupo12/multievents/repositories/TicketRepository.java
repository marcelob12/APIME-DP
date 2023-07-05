package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Ticket;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface TicketRepository extends ListCrudRepository<Ticket, UUID> {
    
}
