package com.example.ebooks.services.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ebooks.services.exceptions.CustomExceptions.EntityNotFoundEbooks;
import com.example.ebooks.services.exceptions.CustomExceptions.NotAuthorizedCustom;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundEbooks.class)
    public ResponseEntity<CustomError> notFound(EntityNotFoundEbooks entity, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError errorBase = new CustomError(LocalDateTime.now(), status.value(), entity.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(errorBase);
    }

    @ExceptionHandler(NotAuthorizedCustom.class)
    public ResponseEntity<CustomError> notAuthorized(NotAuthorizedCustom entity, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        CustomError errorBase = new CustomError(LocalDateTime.now(), status.value(), entity.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(errorBase);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException entity,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError(LocalDateTime.now(), status.value(), "Dados invalidos",
                request.getRequestURI());
        for (FieldError f : entity.getBindingResult().getFieldErrors()) {
            error.addErrors(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }
}