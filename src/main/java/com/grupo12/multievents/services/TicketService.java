package com.grupo12.multievents.services;

import com.grupo12.multievents.models.dtos.SaveTicketDTO;
import com.grupo12.multievents.models.entities.Order;
import com.grupo12.multievents.models.entities.Ticket;
import com.grupo12.multievents.models.entities.Tier;

import java.util.List;

public interface TicketService {

    void saveTicket(Tier tier, Order order) throws Exception;

    Ticket findTicketById(String id);

    List<Ticket> findAllTickets();

    void deleteTicketById(String id) throws Exception;
}
