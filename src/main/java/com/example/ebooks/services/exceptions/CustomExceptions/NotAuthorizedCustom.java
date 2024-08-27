package com.example.ebooks.services.exceptions.CustomExceptions;

public class NotAuthorizedCustom extends RuntimeException {

    public NotAuthorizedCustom() {
        super("Sem autorizacao");
    }

    public NotAuthorizedCustom(String msg) {
        super(msg);
    }
}
