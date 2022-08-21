package exception;

import dtos.ExceptionDTO;

public class RotorIdOutOfBounds extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public RotorIdOutOfBounds(int rotorNumber, int rotorId, int totalRotors){
        exceptionDetails = new ExceptionDTO(
                "rotor number " + rotorNumber,
                "rotor id is out of bounds, the given id is " + rotorId,
                "make sure rotor id's are between 1 and " + totalRotors);
    }

    public RotorIdOutOfBounds(int rotorId, int totalRotors){
        exceptionDetails = new ExceptionDTO(
                "rotor with id: " + rotorId + ".",
                "rotor id is out of total rotor amount bounds.",
                "make sure rotor id is between 1 and " + totalRotors);
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
