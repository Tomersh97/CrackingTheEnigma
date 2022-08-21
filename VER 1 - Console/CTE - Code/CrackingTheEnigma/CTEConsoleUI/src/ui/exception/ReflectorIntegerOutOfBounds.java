package ui.exception;

import dtos.ExceptionDTO;

public class ReflectorIntegerOutOfBounds extends  EnigmaUIException{
    private final ExceptionDTO exceptionDetails;

    public ReflectorIntegerOutOfBounds(int reflectorId, int numOfReflectors) {
        exceptionDetails = new ExceptionDTO(
                "given reflector number: " + reflectorId + ".",
                "is not a number between 1 and "+ numOfReflectors + ".",
                "please choose a number between 1 and " + numOfReflectors + ".");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
