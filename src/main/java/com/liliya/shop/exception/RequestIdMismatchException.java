package com.liliya.shop.exception;

public class RequestIdMismatchException extends RuntimeException {
    public RequestIdMismatchException() {
        super("Request body id doesn't match id in path");
    }

    public RequestIdMismatchException(String message) {
        super(message);
    }

    public RequestIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestIdMismatchException(Throwable cause) {
        super("Request body id doesn't match id in path", cause);
    }
}
