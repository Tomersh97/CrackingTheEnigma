package exception;

import dtos.ExceptionDTO;

public class IntegerNotRomanNumeralException extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public IntegerNotRomanNumeralException(String reflector){
        exceptionDetails = new ExceptionDTO(
                "reflector in XML",
                "the reflector: " + reflector + " is not a roman numeral I~V",
                "make sure the reflector is a roman numeral");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
