package exception;

import dtos.ExceptionDTO;

public class RotorIdDuplicated extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public RotorIdDuplicated(int firstRotorInd, int secondRotorInd, int duplicatedId){
        exceptionDetails = new ExceptionDTO(
                "rotor number " + firstRotorInd + " and rotor number " + secondRotorInd,
                "both rotors share the same ID: " + duplicatedId,
                "make sure rotor ids are different");
    }

    public RotorIdDuplicated(int duplicatedId){
        exceptionDetails = new ExceptionDTO(
                "rotor with id of: " + duplicatedId + "." ,
                "rotor with the same id already chosen",
                "make sure rotor ids are different");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
