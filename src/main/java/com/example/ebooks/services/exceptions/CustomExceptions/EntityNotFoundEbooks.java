package com.example.ebooks.services.exceptions.CustomExceptions;

public class EntityNotFoundEbooks extends RuntimeException {

    public EntityNotFoundEbooks(String msg) {
        super(msg);
    }
}
