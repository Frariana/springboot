package com.springboot.error;

public class EmailExistentException extends Exception {
    public EmailExistentException(String message) {
        super(message);
    }
}