package exception;

import dtos.ExceptionDTO;

public class ReflectorIdDuplicated extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public ReflectorIdDuplicated(int reflectorOne, int reflectorTwo, String id){
        exceptionDetails = new ExceptionDTO(
                "reflector number: "+  reflectorOne + " and reflector number: " + reflectorTwo,
                "they both share the same id: "+ id,
                "make sure the reflector ids are different");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
