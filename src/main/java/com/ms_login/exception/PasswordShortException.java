package com.ms_login.exception;

public class PasswordShortException extends RuntimeException {
    public PasswordShortException(String message) {
        super(message);
    }
}
