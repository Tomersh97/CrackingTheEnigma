package ui.exception;

import dtos.ExceptionDTO;

public abstract class EnigmaUIException extends RuntimeException{
    public abstract ExceptionDTO getExceptionDetails();
}
