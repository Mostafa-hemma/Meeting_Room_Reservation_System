package com.backend.fullProject.customExceptoins;

public class EventOverlapException extends RuntimeException {
    public EventOverlapException(String message) {
        super(message);
    }
}
