package com.eindopdrachtbackend.exception;

public class InvalidInput extends RuntimeException {
    public InvalidInput(String message) {
        super(message);
    }
}