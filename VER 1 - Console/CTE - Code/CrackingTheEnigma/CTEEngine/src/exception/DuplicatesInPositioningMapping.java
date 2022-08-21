package exception;

import dtos.ExceptionDTO;

public class DuplicatesInPositioningMapping extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public DuplicatesInPositioningMapping(int rotorNum, String side, String letter){
        exceptionDetails = new ExceptionDTO(
                "rotor number " + rotorNum,
                "letter " + letter + " is mapped more than once on the " + side,
                "make sure each letter on the " + side + "positioning column only appears once");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
