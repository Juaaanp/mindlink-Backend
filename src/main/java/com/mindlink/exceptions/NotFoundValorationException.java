package com.mindlink.exceptions;

public class NotFoundValorationException extends RuntimeException {

    public NotFoundValorationException() {
        super("Aún no hay valoraciones");
    }
}
