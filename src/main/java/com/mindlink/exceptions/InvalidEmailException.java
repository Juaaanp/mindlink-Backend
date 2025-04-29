package com.mindlink.exceptions;

//Invocado en el register
public class InvalidEmailException extends RuntimeException {
    
    public InvalidEmailException() {
        super("El email digitado es inválido");
    }
}
