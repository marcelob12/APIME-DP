package com.grupo12.multievents.services;

import com.grupo12.multievents.models.entities.Category;

import java.util.List;

public interface CategoryService {
    void saveCategory() throws Exception;

    Category findCategoryById(String id);
    Category findCategoryByName(String name);

    List<Category> findAllCategories();

    void deleteCategoryById(String id) throws Exception;
}
