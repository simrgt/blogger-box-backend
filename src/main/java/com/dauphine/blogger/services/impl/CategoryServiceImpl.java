package com.dauphine.blogger.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final List<Category> temporaryCategories;

    public CategoryServiceImpl() {
        temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }

    @Override
    public List<Category> getAll() {
        return temporaryCategories;
    }

    @Override
    public Category getById(UUID id) {
        return temporaryCategories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public Category create(String name) {
        Category newCategory = new Category(UUID.randomUUID(), name);
        temporaryCategories.add(newCategory);
        return newCategory;
    }

    @Override
    public Category updateName(UUID id, String name) {
        for (int i = 0; i < temporaryCategories.size(); i++) {
            Category category = temporaryCategories.get(i);
            if (category.getId().equals(id)) {
                Category updated = new Category(id, name);
                temporaryCategories.set(i, updated);
                return updated;
            }
        }
        return null; // Category not found
    }

    @Override
    public boolean delete(UUID id) {
        for (int i = 0; i < temporaryCategories.size(); i++) {
            if (temporaryCategories.get(i).getId().equals(id)) {
                temporaryCategories.remove(i);
                return true;
            }
        }
        return false; // Category not found
    }
}
