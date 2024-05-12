package com.example.demo.exception;

public class PaymentSerializationException extends RuntimeException {

    public PaymentSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
