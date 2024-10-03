package com.backend.fullProject.customExceptoins;

public class EventWrongDate extends RuntimeException {
    public EventWrongDate(String message) {
        super(message);
    }

}
