package validators;

import classes.SetForUseChoices;
import machine.EnigmaMachine;
import machine.Keyboard;
import resources.schemas.generated.CTEMachine;

import java.util.List;


///Managing validation class.
///Holds validators for various purposes.
public class ValidationManager{
    private final ValidatorLoadXML xmlValidator = new ValidatorLoadXML();
    private final ValidationMachineSet setValidator = new ValidationMachineSet();
    private final ValidationEncrypt encryptValidator = new ValidationEncrypt();

    public void setMachineLoadXML(CTEMachine checkMachine){
        xmlValidator.setCheckedMachine(checkMachine);
    }

    public void validateEncryption(String message, Keyboard usedKeyboard){
        encryptValidator.validateEncryptedMessage(message, usedKeyboard);
    }

    public void validateLoadXML(){
        xmlValidator.validate();
    }

    public void validateSetMachine(List<String> choices, SetForUseChoices choiceType, EnigmaMachine machine){
        switch (choiceType){
            case ROTOR:
                setValidator.validateChosenRotors(choices, machine.getTotalNumOfRotors());
                break;
            case REFLECTOR:
                setValidator.validateChosenReflector(choices, machine.getTotalNumOfReflectors());
                break;
            case PLUG:
                setValidator.validatePlugs(choices, machine.getUsedKeyboard());
                break;
            case ROTORSLIDEPOS:
                setValidator.validateChosenTopPosition(choices, machine.getUsedKeyboard());
                break;
            case ALLSET:
                machine.setIsMachineUsable(true);
                break;
        }
    }

}
