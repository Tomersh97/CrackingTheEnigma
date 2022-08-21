package ui.exception;

import dtos.ExceptionDTO;

public class InvalidAmountTopPositionsChosen extends EnigmaUIException{
    private final ExceptionDTO exceptionDetails;

    public InvalidAmountTopPositionsChosen(int amountChosen, int amountRequired) {
        exceptionDetails = new ExceptionDTO(
                "amount of top positions for rotors chosen: " + amountChosen + ".",
                "selected machine requires " + amountRequired + " positions for use, you chose: " + amountChosen + ".",
                "please select the correct amount of top positions for use.");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
