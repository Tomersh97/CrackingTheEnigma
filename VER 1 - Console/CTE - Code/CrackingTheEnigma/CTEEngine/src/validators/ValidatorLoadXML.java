package validators;

import exception.*;
import resources.schemas.generated.*;
import utils.Utilities;
import java.util.*;

public class ValidatorLoadXML {

    private CTEMachine xmlMachine = new CTEMachine();
    public void setCheckedMachine(CTEMachine toCheckMachine){
        xmlMachine = toCheckMachine;
    }


    public void validate() {
        validateABCSizeIsValid();
        isRotorCountValid();
        isRotorCountLessOrEqualThanTotalNumOfRotors();
        validateRotorIdsInBounds();
        validateRotorIdsNoDuplicates();
        validatePositioningMappingAllRotors();
        validateAllNotchesInRotors();
        validateReflectorIdsAreRomanNumeral();
        validateReflectorsIdsNoDuplicates();
        validateReflectorIdsInBounds();
        validateNoReflectionsDuplicates();
    }

    private void validateReflectorIdsInBounds(){
        int numOfReflectors = xmlMachine.getCTEReflectors().getCTEReflector().size();
        int reflectorInd = 1;
        for(CTEReflector reflector : xmlMachine.getCTEReflectors().getCTEReflector()){
            if(Utilities.isRomanFrom1To5(reflector.getId().toUpperCase())){
                int romanVal = Utilities.convertRomanItoVToInt(reflector.getId().toUpperCase());
                if(romanVal > numOfReflectors){
                    throw new ReflectorIdOutOfBounds(reflectorInd, reflector.getId().toUpperCase(), numOfReflectors);
                }
            }
            reflectorInd++;
        }
    }

    private void validateReflectorIdsAreRomanNumeral(){
        for(CTEReflector reflector : xmlMachine.getCTEReflectors().getCTEReflector()){
            if(!Utilities.isRomanFrom1To5(reflector.getId().toUpperCase())){
                throw new IntegerNotRomanNumeralException(reflector.getId().toUpperCase());
            }
        }
    }

    private void validateNoReflectionsDuplicates(){
        int reflectorInd = 1;
        for(CTEReflector reflector : xmlMachine.getCTEReflectors().getCTEReflector()){
            validateMappingInReflector(reflector, reflectorInd);
            reflectorInd++;
        }
    }

    private void validateMappingInReflector(CTEReflector reflector, int reflectorInd){
        int reflectInd = 1;
        for(CTEReflect reflection : reflector.getCTEReflect()){
            if(reflection.getInput() == reflection.getOutput()){
                throw new ReflectsToSameLetter(reflectorInd, reflectInd, reflection.getInput());
            }
            reflectInd++;
        }
    }

    private void validateReflectorsIdsNoDuplicates(){
        int ind = 1;
        Map<String, Integer> reflectorId2ReflectorInd = new HashMap<>();
        for(CTEReflector reflector : xmlMachine.getCTEReflectors().getCTEReflector()){
            if(reflectorId2ReflectorInd.containsKey(reflector.getId().toUpperCase())){
                throw new ReflectorIdDuplicated(ind,reflectorId2ReflectorInd.get(reflector.getId().toUpperCase()),reflector.getId().toUpperCase());
            }
            reflectorId2ReflectorInd.put(reflector.getId().toUpperCase(), ind);
            ind++;
        }
    }

    private void validatePositioningMappingAllRotors(){
        int rotorInd = 1;
        CTERotors rotors = xmlMachine.getCTERotors();
        for(CTERotor rotor : rotors.getCTERotor()){
            validatePositioningMappingRotor(rotor, rotorInd);
            rotorInd++;
        }
    }

    private void validateAllNotchesInRotors(){
        int rotorInd = 1;
        int abcSize = xmlMachine.getABC().length();
        for(CTERotor rotor : xmlMachine.getCTERotors().getCTERotor()){
            validateNotchInBounds(rotor.getNotch(), abcSize, rotorInd);
            rotorInd++;
        }
    }

    private void validateNotchInBounds(int notchInd, int abcSize, int rotorInd){
        if(notchInd < 1 || notchInd > abcSize){
            throw new NotchOutOfBounds(rotorInd, notchInd, abcSize);
        }
    }

    private void validatePositioningMappingRotor(CTERotor rotor, int ind){
        Map<String, Integer> positioningSetLeft = new HashMap<>();
        Map<String, Integer> positioningSetRight = new HashMap<>();
        for(CTEPositioning positioning : rotor.getCTEPositioning()){
            if(positioningSetLeft.containsKey(positioning.getLeft().toUpperCase()) || positioningSetRight.containsKey(positioning.getRight().toUpperCase())) {
                String letter = positioningSetLeft.containsKey(positioning.getLeft().toUpperCase()) ? positioning.getLeft().toUpperCase() : positioning.getRight().toUpperCase();
                String side = positioningSetLeft.containsKey(positioning.getLeft().toUpperCase()) ? "left" : "right";
                throw new DuplicatesInPositioningMapping(ind, side, letter);
            }
            positioningSetLeft.put(positioning.getLeft().toUpperCase(), ind);
            positioningSetRight.put(positioning.getRight().toUpperCase(), ind);
        }
    }

    private void validateRotorIdsInBounds() {
        CTERotors rotors = xmlMachine.getCTERotors();
        int ind = 1;
        int numRotors = rotors.getCTERotor().size();
        for (CTERotor rotor : rotors.getCTERotor()) {
            if (rotor.getId() < 1 || rotor.getId() > numRotors) {
                throw new RotorIdOutOfBounds(ind, rotor.getId(), numRotors);
            }
            ind++;
        }
    }

    private void validateRotorIdsNoDuplicates(){
        CTERotors rotors = xmlMachine.getCTERotors();
        int ind = 1;
        Map<Integer, Integer> rotorsMap = new HashMap<>();
        for(CTERotor rotor : rotors.getCTERotor()){
            if(rotorsMap.containsKey(rotor.getId())){
                throw new RotorIdDuplicated(ind, rotorsMap.get(rotor.getId()), rotor.getId());
            }
            rotorsMap.put(rotor.getId(), ind);
            ind++;
        }
    }

    private void validateABCSizeIsValid(){
        xmlMachine.setABC(xmlMachine.getABC().trim());
        String abc = xmlMachine.getABC().toUpperCase();
        int abcSize = abc.length();
        if(abcSize % 2 != 0) {
            throw new ABCNotValid(abc.toUpperCase());
        }
    }

    private void isRotorCountLessOrEqualThanTotalNumOfRotors(){
        int totalNumOfRotors = xmlMachine.getCTERotors().getCTERotor().size();
        int rotorCount = xmlMachine.getRotorsCount();
        if(rotorCount > totalNumOfRotors){
            throw new RotorCountBiggerThanRotorsTotal(rotorCount, totalNumOfRotors);
        }
    }

    private void isRotorCountValid(){
        int rotorCount = xmlMachine.getRotorsCount();
        if(rotorCount < 2){
            throw new RotorCountLessThanTwo(rotorCount);
        }
    }
}
