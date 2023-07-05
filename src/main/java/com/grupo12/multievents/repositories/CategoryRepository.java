package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface CategoryRepository extends ListCrudRepository<Category, UUID> {
    Category findByName(String name);

}
