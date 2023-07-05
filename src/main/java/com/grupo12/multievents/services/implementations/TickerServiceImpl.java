package com.grupo12.multievents.services.implementations;

import com.grupo12.multievents.models.entities.Order;
import com.grupo12.multievents.models.entities.Ticket;
import com.grupo12.multievents.models.entities.Tier;
import com.grupo12.multievents.repositories.TicketRepository;
import com.grupo12.multievents.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TickerServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public void saveTicket(Tier tier, Order order) throws Exception {
        Ticket newTicket = new Ticket(tier, order);
        ticketRepository.save(newTicket);
    }

    @Override
    public Ticket findTicketById(String id) {return ticketRepository.findById(UUID.fromString(id)).orElse(null);}

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void deleteTicketById(String id) throws Exception {
        ticketRepository.delete(ticketRepository.findById(UUID.fromString(id)).orElse(null));
    }
}
