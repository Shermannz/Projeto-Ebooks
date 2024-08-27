package com.example.ebooks.services.exceptions.CustomExceptions;

public class EntityNotFoundEbooks extends RuntimeException {

    public EntityNotFoundEbooks() {
        super("Usuario não encontrado");
    }

    public EntityNotFoundEbooks(String msg) {
        super(msg);
    }
}
