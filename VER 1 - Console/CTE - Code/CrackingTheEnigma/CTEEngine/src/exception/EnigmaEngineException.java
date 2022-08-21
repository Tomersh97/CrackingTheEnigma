package exception;

import dtos.ExceptionDTO;

public abstract class EnigmaEngineException extends RuntimeException{
    public abstract ExceptionDTO getExceptionDetails();
}
