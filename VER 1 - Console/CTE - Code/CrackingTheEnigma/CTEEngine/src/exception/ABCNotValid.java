package exception;

import dtos.ExceptionDTO;

public class ABCNotValid extends  EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;
    public ABCNotValid(String ABC){
        exceptionDetails = new ExceptionDTO(
                "given keyboard: " + ABC,
                "keyboard size is odd, given ABC size is: " + ABC.length(),
                "make sure ABC size is even!");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
