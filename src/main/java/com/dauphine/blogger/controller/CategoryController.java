package com.dauphine.blogger.controller;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
        summary = "Get all categories",
        description = "Returns all categories"
    )
    public List<Category> getCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get category by id",
        description = "Returns category by id"
    )
    public Category getCategoryById(@RequestBody UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(
        summary = "Create category",
        description = "Creates a new category"
    )
    public Category createCategory(@RequestBody Category category) {
        return service.create(category.getName());
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update category",
        description = "Updates an existing category"
    )
    public Category updateCategory(@PathVariable UUID id, @RequestBody Category category) {
        return service.updateName(id, category.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete category",
        description = "Deletes an existing category"
    )
    public boolean deleteCategory(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}
