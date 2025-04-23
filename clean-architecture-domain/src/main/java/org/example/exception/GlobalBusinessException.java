package org.example.exception;

public class GlobalBusinessException extends RuntimeException {
    public GlobalBusinessException(String message) {
        super(message);
    }
}
