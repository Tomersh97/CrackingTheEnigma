package ui.exception;

import dtos.ExceptionDTO;

public class PlugsChosenInvalidSize extends  EnigmaUIException{
    private final ExceptionDTO exceptionDetails;

    public PlugsChosenInvalidSize(String plugs) {
        exceptionDetails = new ExceptionDTO(
                "plugs chosen: "+ plugs +".",
                "size of the plugs inserted must be even. The plugs given contain: "+plugs.length()+" characters.",
                "make sure plugs are even sized.");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
