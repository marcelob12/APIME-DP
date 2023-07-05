package com.grupo12.multievents.controllers;

import com.grupo12.multievents.models.dtos.MessageDTO;
import com.grupo12.multievents.models.dtos.SaveOrderDTO;
import com.grupo12.multievents.models.dtos.SaveTierDTO;
import com.grupo12.multievents.models.entities.Event;
import com.grupo12.multievents.models.entities.Order;
import com.grupo12.multievents.models.entities.Tier;
import com.grupo12.multievents.models.entities.User;
import com.grupo12.multievents.services.OrderService;
import com.grupo12.multievents.services.UserService;
import com.grupo12.multievents.utils.RequestErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RequestErrorHandler requestErrorHandler;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> SaveOrder(@RequestBody SaveOrderDTO info, BindingResult validations) {
        if (validations.hasErrors())
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);

        User user = userService.findUserByIdentifier(info.getIdentifier());

        if (user == null)
            return new ResponseEntity<>(new MessageDTO("Usuario no existente"), HttpStatus.NOT_FOUND);

        try {
            orderService.saveOrder(info, user);
            return new ResponseEntity<>(info,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/all")
    public ResponseEntity<?> findAllOrders() {
        return new ResponseEntity<>(orderService.findAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/all/{identifier}")
    public ResponseEntity<?> findAllOrdersById(@PathVariable("identifier") String identifier) {
        User userFound = userService.findUserByIdentifier(identifier);

        if (userFound == null) {
            return new ResponseEntity<>(new MessageDTO("Usuario no existente"), HttpStatus.NOT_FOUND);
        }

        List<Order> userOrders = orderService.findAllOrders()
                .stream()
                .filter(order -> order.getUser().equals(userFound))
                .collect(Collectors.toList());


        try {
            return new ResponseEntity<>(userOrders, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderId(@PathVariable("id") String id) {
        System.out.println("Controller" + id);
        Order order = orderService.findOrderById(id);

        if (order == null) {
            return new ResponseEntity<>(new MessageDTO("El id de la orden es inválida"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);

    }


}
