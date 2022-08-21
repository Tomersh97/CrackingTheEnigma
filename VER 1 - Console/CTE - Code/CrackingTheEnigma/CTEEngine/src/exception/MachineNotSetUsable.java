package exception;

import dtos.ExceptionDTO;

public class MachineNotSetUsable extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public MachineNotSetUsable(){
        exceptionDetails = new ExceptionDTO(
                "order of actions.",
                "current machine has not been set up for use yet.",
                "use actions 3 or 4 to set up the loaded machine.");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
