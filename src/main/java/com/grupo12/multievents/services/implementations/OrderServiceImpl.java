package com.grupo12.multievents.services.implementations;

import com.grupo12.multievents.models.dtos.SaveOrderDTO;
import com.grupo12.multievents.models.entities.Order;
import com.grupo12.multievents.models.entities.User;
import com.grupo12.multievents.repositories.OrderRepository;
import com.grupo12.multievents.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void saveOrder(SaveOrderDTO data, User user) throws Exception {
        Order newOrder = new Order(data.getTotal(), data.getDate(), user);
        orderRepository.save(newOrder);

    }

    @Override
    public Order findOrderById(String id) {
        return orderRepository.findById(UUID.fromString(id)).orElse(null);
    }

    @Override
    public List<Order> findOrderByUser(UUID id) {
        return orderRepository.findByUserIdUser(id);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrderById(String id) throws Exception {
        orderRepository.delete(orderRepository.findById(UUID.fromString(id)).orElse(null));
    }
}
