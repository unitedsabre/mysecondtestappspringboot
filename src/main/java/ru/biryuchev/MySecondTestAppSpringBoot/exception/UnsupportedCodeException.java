package ru.biryuchev.MySecondTestAppSpringBoot.exception;

public class UnsupportedCodeException extends Exception {
    public UnsupportedCodeException (String message) {
        super(message);
    }
}