package com.example.picpaybackend.authorization;

public class UnauthorizedTransactioException extends RuntimeException {

    public UnauthorizedTransactioException(String message) {
        super(message);
    }
}
