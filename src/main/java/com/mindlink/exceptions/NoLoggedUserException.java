package com.mindlink.exceptions;

public class NoLoggedUserException extends RuntimeException {

    public NoLoggedUserException() {
        super("No logged user");
    }
}
