package exception;

import dtos.ExceptionDTO;

public class RotorCountBiggerThanRotorsTotal extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public RotorCountBiggerThanRotorsTotal(int rotorCount, int totalRotors){
        exceptionDetails = new ExceptionDTO(
                "rotor-count in XML",
                "rotor-count value is less than total amount of rotors. Given rotor-count: "+ rotorCount  +
                        " while there are only " + totalRotors + " rotors",
                "make sure rotor-count is greater or equal to " + totalRotors);
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
