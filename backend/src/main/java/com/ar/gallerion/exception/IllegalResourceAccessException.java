package com.ar.gallerion.exception;

public class IllegalResourceAccessException extends RuntimeException {
    public IllegalResourceAccessException(String message) {
        super(message);
    }

    public IllegalResourceAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
