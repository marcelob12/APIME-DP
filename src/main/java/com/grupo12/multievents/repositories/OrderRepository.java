package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Order;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends ListCrudRepository<Order, UUID> {
    List<Order> findByUserIdUser(UUID id);
}
