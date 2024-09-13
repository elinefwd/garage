package com.eindopdrachtbackend.exception;

public class Invalidinput extends RuntimeException {
    public Invalidinput (String message) {
        super(message);
    }
}