package com.pedrosa.dscatalog.exceptions;

public class ControllerNotFoundException extends RuntimeException{

    public ControllerNotFoundException(String msg) {
        super(msg);
    }
}
