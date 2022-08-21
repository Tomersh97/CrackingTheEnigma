package exception;

import dtos.ExceptionDTO;

public class PlugLettersDuplicated extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public PlugLettersDuplicated(String plug){
        exceptionDetails = new ExceptionDTO(
                "chosen letters in plug: " + plug + ".",
                "plug letters are duplicated.",
                "make sure the plug letters are different and part of the ABC.");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
