package com.kovaliv.exceptions;

public class DontValidNumberException extends Exception {
    private String message;

    public DontValidNumberException(String message) {
        this.message = message;
    }

    public DontValidNumberException() {
        message = "Зображення не відповідає жодному з шаблонів";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
