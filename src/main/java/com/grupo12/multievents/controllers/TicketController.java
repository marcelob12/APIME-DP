package com.grupo12.multievents.controllers;

import com.grupo12.multievents.models.dtos.MessageDTO;
import com.grupo12.multievents.models.dtos.OrderDTO;
import com.grupo12.multievents.models.dtos.SaveOrderDTO;
import com.grupo12.multievents.models.dtos.SaveTicketDTO;
import com.grupo12.multievents.models.entities.Order;
import com.grupo12.multievents.models.entities.Ticket;
import com.grupo12.multievents.models.entities.Tier;
import com.grupo12.multievents.models.entities.User;
import com.grupo12.multievents.repositories.OrderRepository;
import com.grupo12.multievents.services.OrderService;
import com.grupo12.multievents.services.TicketService;
import com.grupo12.multievents.services.TierService;
import com.grupo12.multievents.services.UserService;
import com.grupo12.multievents.utils.RequestErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    RequestErrorHandler requestErrorHandler;

    @Autowired
    TicketService ticketService;

    @Autowired
    TierService tierService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> saveTicket(@RequestBody SaveTicketDTO info, BindingResult validations) {
        if (validations.hasErrors())
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);

        Tier tier = tierService.findTierById(info.getTier());
        Order order = orderService.findOrderById(info.getOrder());

        if (tier == null || order == null)
            return new ResponseEntity<>(new MessageDTO("Datos insuficientes"), HttpStatus.NOT_FOUND);

        try {
            ticketService.saveTicket(tier, order);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new MessageDTO("Se agrego ticket a la compra"), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllTicket() {

        return new ResponseEntity<>(ticketService.findAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTicketById(@PathVariable("id") String id) {

        Ticket ticket = ticketService.findTicketById(id);
        if (ticket == null)
            return new ResponseEntity<>(new MessageDTO("El id de la orden es inválida"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/my-tickets")
    public ResponseEntity<?> findAllTicketById() {
        User user = userService.findUserAuthenticated();
        List<Order> orders = orderService.findOrderByUser(user.getIdUser());
        List<OrderDTO> formatOrders = new ArrayList<OrderDTO>();

        if (user == null) {
            return new ResponseEntity<>(new MessageDTO("Usuario no existente"), HttpStatus.NOT_FOUND);
        }

        orders.stream().forEach(order -> {
            formatOrders.add(new OrderDTO(order.getId().toString(), order.getTotal(), order.getDate(), order.getTickets()));
        });

        return new ResponseEntity<>(formatOrders, HttpStatus.OK);
    }


}
