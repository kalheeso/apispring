package com.matheus.apispring.services.exceptions;

public class NullFieldException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NullFieldException(String msg) {
        super(msg);
    }

    public NullFieldException(String msg, Throwable cause) {
        super(msg, cause);
    }
}