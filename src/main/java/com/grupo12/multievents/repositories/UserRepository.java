package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface UserRepository extends ListCrudRepository<User, UUID> {
    User findByEmail(String email);
    User findByEmailOrUsername(String email, String username);
    User findByConfirmationCode(String confirmationCode);
}
