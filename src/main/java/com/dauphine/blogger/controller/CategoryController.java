package com.dauphine.blogger.controller;

import com.dauphine.blogger.exception.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        description = "Returns all categories or filtered by name"
    )
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name) {
        List<Category> categories = name == null || name.isBlank() 
        ? service.getAll() 
        : service.getAllLikeName(name);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get category by id",
        description = "Returns category by id"
    )
    public ResponseEntity<Category> getById(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        Category category = service.getById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    @Operation(
        summary = "Create category",
        description = "Creates a new category"
    )
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = service.create(category.getName());
        return ResponseEntity.status(201).body(createdCategory);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update category",
        description = "Updates an existing category"
    )
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody Category category) throws CategoryNotFoundByIdException {
        return ResponseEntity.ok(service.updateName(id, category.getName()));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete category",
        description = "Deletes an existing category"
    )
    public ResponseEntity<Boolean> deleteCategory(@PathVariable UUID id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
