package exception;

import dtos.ExceptionDTO;

public class PlugLetterIsNotPartOfABC extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public PlugLetterIsNotPartOfABC(Character plugLetter, String ABC){
        exceptionDetails = new ExceptionDTO(
                "chosen letter in plug: (" + plugLetter + ").",
                "plug is not a part of the keyboard in the machine: " + ABC + ".",
                "make sure the plug letters are part of the ABC.");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
