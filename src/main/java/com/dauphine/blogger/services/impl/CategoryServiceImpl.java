package com.dauphine.blogger.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Category create(String name) {
        Category newCategory = new Category(UUID.randomUUID(), name);
        return repository.save(newCategory);
    }

    @Override
    public Category updateName(UUID id, String name) {
        Category category = repository.findById(id).orElse(null);
        if (category != null) {
            category.setName(name);
            return repository.save(category);
        }
        return null; // Category not found
    }

    @Override
    public boolean deleteById(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false; // Category not found
    }
}
