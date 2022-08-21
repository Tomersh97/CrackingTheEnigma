package validators;
import exception.*;
import interfaces.general.Resetable;
import machine.Keyboard;
import utils.Utilities;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationMachineSet{

    public void validatePlugs(List<String> chosenPlugs, Keyboard usedKeyboard) {
        Set<Character> insertedPlugs = new HashSet<>();
        for(String plug : chosenPlugs) {
            if (!(usedKeyboard.isCharacterInAbc(plug.charAt(0)))) {
                throw new PlugLetterIsNotPartOfABC(plug.charAt(0), usedKeyboard.getKeyboardABC());
            }
            if (!(usedKeyboard.isCharacterInAbc(plug.charAt(1)))) {

                throw new PlugLetterIsNotPartOfABC(plug.charAt(1), usedKeyboard.getKeyboardABC());
            } else if (plug.charAt(0) == plug.charAt(1)) {
                throw new PlugLettersDuplicated(plug);
            } else {
                if (insertedPlugs.contains(plug.charAt(0))) {
                    throw new PlugLetterInUse(plug.charAt(0));
                }
                if (insertedPlugs.contains(plug.charAt(1))) {
                    throw new PlugLetterInUse(plug.charAt(1));
                }
                insertedPlugs.add(plug.charAt(0));
                insertedPlugs.add(plug.charAt(1));
            }
        }
    }

    public void validateChosenReflector(List<String> reflectors, int amountReflectors){
        for (String reflector : reflectors){
            if (!Utilities.isRomanFrom1To5(reflector)) {
                throw new IntegerNotRomanNumeralException(reflector);
            } else {
                int romanVal = Utilities.convertRomanItoVToInt(reflector);
                if (romanVal > amountReflectors) {
                    throw new ReflectorIdOutOfBounds(reflector, amountReflectors);
                }
            }
        }
    }

    public void validateChosenTopPosition(List<String> chosenTopPositions, Keyboard checkedKeyboard) {
        for(String chosenTopPosition : chosenTopPositions) {
            if (!checkedKeyboard.isCharacterInAbc(chosenTopPosition.charAt(0))) {
                throw new CharacterNotExistInABC(chosenTopPosition, checkedKeyboard.getKeyboardABC());
            }
        }
    }

    public void validateChosenRotors(List<String> chosenRotors, int totalNumOfRotors) {
        Set<String> insertedRotors = new HashSet<>();
        for (String chosenRotor : chosenRotors) {
            try {
                int rotorIdNum = Integer.parseInt(chosenRotor);
                if (rotorIdNum < 1 || rotorIdNum > totalNumOfRotors) {
                    throw new RotorIdOutOfBounds(rotorIdNum, totalNumOfRotors);
                }

                if (insertedRotors.contains(chosenRotor)) {
                    throw new RotorIdDuplicated(rotorIdNum);
                }
                insertedRotors.add(chosenRotor);
            } catch (NumberFormatException e) {
                throw new ParsingToIntegerException(chosenRotor);
            }
        }
    }
}
