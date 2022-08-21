package exception;

import dtos.ExceptionDTO;

public class NotchOutOfBounds extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public NotchOutOfBounds(int rotorNum, int notchPos, int abcSize){
        exceptionDetails = new ExceptionDTO(
                "notch in rotor number " + rotorNum,
                "notch position: " + notchPos + " exceeds the bounds of the ABC",
                "make sure notch position is between 1 and " + abcSize);
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
