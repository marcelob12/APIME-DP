package com.grupo12.multievents.services;

import com.grupo12.multievents.models.dtos.SaveOrderDTO;
import com.grupo12.multievents.models.entities.Order;
import com.grupo12.multievents.models.entities.User;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    void saveOrder(SaveOrderDTO data, User user) throws Exception;

    Order findOrderById(String id);

    List<Order> findOrderByUser(UUID id);

    List<Order> findAllOrders();

    void deleteOrderById(String id) throws Exception;
}
