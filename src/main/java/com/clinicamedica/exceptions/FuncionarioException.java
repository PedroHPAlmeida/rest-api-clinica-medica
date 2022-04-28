package com.clinicamedica.exceptions;

public class FuncionarioException extends RuntimeException{

    public FuncionarioException(String message) {
        super(message);
    }

    public FuncionarioException(Throwable t) {
        super(t);
    }
}
