package exception;

import dtos.ExceptionDTO;

public class CharacterNotExistInABC extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public CharacterNotExistInABC(String character, String ABC){
        exceptionDetails = new ExceptionDTO(
                "character chosen: " + character + ".",
                "is not a part of the keyboard: " + ABC + ".",
                "make sure the chosen character is a part of the ABC");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
