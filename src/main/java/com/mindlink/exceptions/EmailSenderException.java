package com.mindlink.exceptions;

public class EmailSenderException extends RuntimeException {
    
    public EmailSenderException() {
        super("Error al enviar el correo de registro");
    }
}
