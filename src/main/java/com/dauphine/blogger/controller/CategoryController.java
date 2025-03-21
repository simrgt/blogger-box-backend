package com.dauphine.blogger.controller;

import com.dauphine.blogger.models.Category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final List<Category> temporaryCategories;

    public CategoryController() {
        temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }

    @GetMapping
    @Operation(
        summary = "Get all categories",
        description = "Returns all categories"
    )
    public List<Category> getCategories() {
        return temporaryCategories;
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get category by id",
        description = "Returns category by id"
    )
    public Category getCategoryById(@RequestBody UUID id) {
        return temporaryCategories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @PostMapping
    @Operation(
        summary = "Create category",
        description = "Creates a new category"
    )
    public Category createCategory(@RequestBody Category category) {
        Category newCategory = new Category(UUID.randomUUID(), category.getName());
        temporaryCategories.add(newCategory);
        return newCategory;
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update category",
        description = "Updates an existing category"
    )
    public Category updateCategory(@PathVariable UUID id, @RequestBody Category category) {
        return temporaryCategories.stream()
                .filter(existingCategory -> existingCategory.getId().equals(id))
                .findFirst()
                .map(existingCategory -> {
                    existingCategory.setName(category.getName());
                    return existingCategory;
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete category",
        description = "Deletes an existing category"
    )
    public void deleteCategory(@PathVariable UUID id) {
        Category categoryToRemove = temporaryCategories.stream()
                .filter(existingCategory -> existingCategory.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        temporaryCategories.remove(categoryToRemove);
    }
}
