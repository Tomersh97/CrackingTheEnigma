package exception;

import dtos.ExceptionDTO;

public class PlugLetterInUse extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public PlugLetterInUse(Character plugLetter){
        exceptionDetails = new ExceptionDTO(
                "chosen letter in plug: " + plugLetter + ".",
                "plug letter is already in use in a different plug.",
                "make sure the plug letter is not in use in a different plug");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
