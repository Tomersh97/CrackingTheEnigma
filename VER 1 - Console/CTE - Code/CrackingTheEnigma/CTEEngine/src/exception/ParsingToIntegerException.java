package exception;

import dtos.ExceptionDTO;

public class ParsingToIntegerException extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public ParsingToIntegerException(String attemptedParse){
        exceptionDetails = new ExceptionDTO(
                "given string: " + attemptedParse + ".",
                "is not a valid integer.",
                "make sure your choice is a valid integer");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
