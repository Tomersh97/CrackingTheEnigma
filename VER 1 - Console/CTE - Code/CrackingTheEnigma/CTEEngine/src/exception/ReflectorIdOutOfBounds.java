package exception;

import dtos.ExceptionDTO;
import utils.Utilities;

public class ReflectorIdOutOfBounds extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public ReflectorIdOutOfBounds(int reflectorNum, String id, int totalNumReflectors){
        exceptionDetails = new ExceptionDTO(
                "reflector number: "+  reflectorNum ,
                "reflector id is greater than total amount of reflectors, the id is: " +
                        id +
                        " while there are " +
                        totalNumReflectors +
                        " reflectors",
                "make sure the reflector id is between I and " + Utilities.convertInteger1to5ToRoman(totalNumReflectors));
    }

    public ReflectorIdOutOfBounds(String id, int totalNumReflectors){
        exceptionDetails = new ExceptionDTO(
                "reflector with id: "+  id ,
                "reflector id is greater than total amount of reflectors, there are " +
                        totalNumReflectors +
                        " reflectors",
                "make sure the reflector id is between I and " + Utilities.convertInteger1to5ToRoman(totalNumReflectors));
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
