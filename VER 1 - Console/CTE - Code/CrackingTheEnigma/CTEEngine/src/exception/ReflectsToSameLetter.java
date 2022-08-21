package exception;

import dtos.ExceptionDTO;
import utils.Utilities;

public class ReflectsToSameLetter extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public ReflectsToSameLetter(int reflectorNum, int reflectNum, int duplicateInputOutput){
        exceptionDetails = new ExceptionDTO(
                "reflector number: "+  reflectorNum + " and reflect in reflector: " + reflectNum,
                "reflect input and output are the same: " + duplicateInputOutput,
                "make sure the reflect's input is not the same as the output");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
