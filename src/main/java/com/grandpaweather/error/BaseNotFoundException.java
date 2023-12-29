package com.grandpaweather.error;

public abstract class BaseNotFoundException extends RuntimeException {
    public BaseNotFoundException(String message) {
        super(message);
    }
}
