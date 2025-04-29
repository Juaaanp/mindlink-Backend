package com.mindlink.exceptions;

//Invocado en el login
public class InvalidCredentialsException extends RuntimeException {
    
    public InvalidCredentialsException() {
        super("Credenciales inválidas");
    }
}
