package exception;

import dtos.ExceptionDTO;

public class MachineNotLoadedException extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public MachineNotLoadedException(){
        exceptionDetails = new ExceptionDTO(
                "order of actions",
                "a valid enigma machine has not been loaded yet.",
                "load a new machine using the 'load machine from xml' action first");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
