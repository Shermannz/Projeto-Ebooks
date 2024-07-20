package com.example.ebooks.services.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends CustomError {
    private List<FieldMessage> fields = new ArrayList<>();

    public ValidationError(LocalDateTime timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addErrors(String fieldName, String message) {
        fields.add(new FieldMessage(fieldName, message));
    }

}
