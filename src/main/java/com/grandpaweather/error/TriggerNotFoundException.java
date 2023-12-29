package com.grandpaweather.error;

public class TriggerNotFoundException extends BaseNotFoundException {
    public TriggerNotFoundException(String message) {
        super("Trigger not found by name "+message);
    }
    public TriggerNotFoundException() {
        super("Trigger not found by name");
    }


}
