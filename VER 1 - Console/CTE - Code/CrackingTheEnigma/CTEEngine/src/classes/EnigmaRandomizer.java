package classes;

import machine.EnigmaMachine;
import utils.Utilities;

import java.util.*;

public class EnigmaRandomizer {

    public void randomizeMachine(EnigmaMachine enigmaMachine){
        randomizeRotorsForUse(enigmaMachine);
        randomizeRotorTopPositionsForUse(enigmaMachine);
        randozimeReflectorForUse(enigmaMachine);
        randomizePlugsForUse(enigmaMachine);
    }

    private void randomizePlugsForUse(EnigmaMachine enigmaMachine){
        int howManyPlugs = new Random().nextInt(enigmaMachine.getUsedKeyboard().getKeyboardSize()/2 + 1);
        Set<String> chosenPlugLetters = new HashSet<>();
        int chosenPlugLetterInd;
        Random plugRandomizer = new Random();
        String randomizedFirstLetter;
        String randomizedSecondLetter;
        for(int i = 0 ; i< howManyPlugs; i++){
            chosenPlugLetterInd = plugRandomizer.nextInt(enigmaMachine.getUsedKeyboard().getKeyboardSize());
            randomizedFirstLetter = enigmaMachine.getUsedKeyboard().getIndexToCharMapping(chosenPlugLetterInd);
            chosenPlugLetterInd = plugRandomizer.nextInt(enigmaMachine.getUsedKeyboard().getKeyboardSize());
            randomizedSecondLetter = enigmaMachine.getUsedKeyboard().getIndexToCharMapping(chosenPlugLetterInd);
            if(chosenPlugLetters.contains(randomizedFirstLetter)
                    || chosenPlugLetters.contains(randomizedSecondLetter)
                    || randomizedFirstLetter.equals(randomizedSecondLetter)){
                i--;
            }
            else{
                enigmaMachine.setPlug(randomizedFirstLetter,randomizedSecondLetter);
                chosenPlugLetters.add(randomizedFirstLetter);
                chosenPlugLetters.add(randomizedSecondLetter);
            }
        }
    }

    private void randozimeReflectorForUse(EnigmaMachine enigmaMachine){
        int reflectorChosen = new Random().nextInt(enigmaMachine.getTotalNumOfReflectors());
        enigmaMachine.setUsedReflector(Utilities.convertInteger1to5ToRoman(reflectorChosen));
    }

    private void randomizeRotorTopPositionsForUse(EnigmaMachine enigmaMachine){
        int sizeABC = enigmaMachine.getUsedKeyboard().getKeyboardSize();
        Random randomTopPositionChooser = new Random();
        int chosenLetter;
        for(int i = 0 ; i < enigmaMachine.getNumOfRotorsInUse(); i++){
            chosenLetter = randomTopPositionChooser.nextInt(sizeABC);
            enigmaMachine.setRotorToTop(i, enigmaMachine.getUsedKeyboard().getIndexToCharMapping(chosenLetter));
        }
    }

    private void randomizeRotorsForUse(EnigmaMachine enigmaMachine){
        List<String> rotors = new ArrayList<>(enigmaMachine.getAllRotors().keySet());
        Collections.shuffle(rotors);
        int numRotorsInUse = enigmaMachine.getNumOfRotorsInUse();
        int ind = 0;
        for(String rotorId : rotors){
            enigmaMachine.setUsedRotor(rotorId);
            ind++;
            if(ind == numRotorsInUse){
                break;
            }
        }
    }
}
