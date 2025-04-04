package com.dauphine.blogger.exception;

import java.util.UUID;

public class PostNotFoundByIdException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PostNotFoundByIdException(UUID id) {
        super("Post not found with id: " + id);
    }

}
