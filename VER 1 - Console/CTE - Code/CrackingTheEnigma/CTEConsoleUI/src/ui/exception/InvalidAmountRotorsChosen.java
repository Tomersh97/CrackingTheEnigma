package ui.exception;

import dtos.ExceptionDTO;

public class InvalidAmountRotorsChosen extends EnigmaUIException {
    private final ExceptionDTO exceptionDetails;

    public InvalidAmountRotorsChosen(int amountChosen, int amountRequired) {
        exceptionDetails = new ExceptionDTO(
                "amount of rotors chosen: " + amountChosen + ".",
                "selected machine requires ." + amountRequired + " rotors for use, you chose: " + amountChosen + ".",
                "please select the correct amount of rotors for use.");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
