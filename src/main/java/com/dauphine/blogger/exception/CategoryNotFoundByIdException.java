package com.dauphine.blogger.exception;

import java.util.UUID;

public class CategoryNotFoundByIdException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CategoryNotFoundByIdException(UUID id) {
        super("Category not found with id: " + id);
    }

}
