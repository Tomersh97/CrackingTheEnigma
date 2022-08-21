package exception;

import dtos.ExceptionDTO;

public class RotorCountLessThanTwo extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public RotorCountLessThanTwo(int rotorCount){
        exceptionDetails = new ExceptionDTO(
                "rotor-count in XML",
                "rotor-count value is less than 2. Given rotor-count: "+ rotorCount,
                "make sure rotor-count is greater or equal to 2");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
