package com.dauphine.blogger.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dauphine.blogger.exception.CategoryNotFoundByIdException;
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
    public List<Category> getAllLikeName(String name) {
        return repository.findAllLikeName(name);
    }

    @Override
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundByIdException(id));

    }

    @Override
    public Category create(String name) {
        Category newCategory = new Category(UUID.randomUUID(), name);
        return repository.save(newCategory);
    }

    @Override
    public Category updateName(UUID id, String name) throws CategoryNotFoundByIdException {
        Category category = getById(id);
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
