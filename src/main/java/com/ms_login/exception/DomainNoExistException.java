package com.ms_login.exception;

public class DomainNoExistException extends RuntimeException {
    public DomainNoExistException(String message) {
        super(message);
    }
}
