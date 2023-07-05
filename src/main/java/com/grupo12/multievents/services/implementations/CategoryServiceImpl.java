package com.grupo12.multievents.services.implementations;

import com.grupo12.multievents.models.entities.Category;
import com.grupo12.multievents.repositories.CategoryRepository;
import com.grupo12.multievents.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void saveCategory() throws Exception {

    }

    @Override
    public Category findCategoryById(String id) {
        return null;
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> findAllCategories() {
        return null;
    }

    @Override
    public void deleteCategoryById(String id) throws Exception {

    }
}
