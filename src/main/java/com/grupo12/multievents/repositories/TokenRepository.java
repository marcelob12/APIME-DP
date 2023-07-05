package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Token;
import com.grupo12.multievents.models.entities.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface TokenRepository extends ListCrudRepository<Token, UUID> {
    List<Token> findByUserAndActive(User user, Boolean active);
}
