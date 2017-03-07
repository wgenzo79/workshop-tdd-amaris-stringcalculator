package com.amaris.exception;

public class NegativesNotAllowedException extends Exception {
    public NegativesNotAllowedException() {
    }

    public NegativesNotAllowedException(String message) {
        super(message);
    }
}
