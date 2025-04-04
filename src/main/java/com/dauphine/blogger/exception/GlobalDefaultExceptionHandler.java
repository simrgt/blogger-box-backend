package com.dauphine.blogger.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class.getName());

    @ExceptionHandler({
        CategoryNotFoundByIdException.class,
        PostNotFoundByIdException.class,
    })
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
        logger.warn("[NOT FOUND] {}", ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }

}
