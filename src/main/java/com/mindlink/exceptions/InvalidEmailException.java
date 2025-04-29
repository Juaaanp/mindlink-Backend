package com.mindlink.exceptions;

public class InvalidEmailException extends RuntimeException {
    
    public InvalidEmailException() {
        super("El email digitado es inválido");
    }
}
