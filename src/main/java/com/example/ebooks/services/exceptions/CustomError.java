package com.example.ebooks.services.exceptions;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomError {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

    public CustomError(LocalDateTime timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

}
