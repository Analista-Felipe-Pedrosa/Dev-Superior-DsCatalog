package com.pedrosa.dscatalog.exceptions;

import lombok.Data;

import java.time.Instant;

@Data
public class StandardErro {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
