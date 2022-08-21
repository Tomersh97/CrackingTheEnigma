package classes;

import machine.*;
import resources.schemas.generated.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MachinePartCreator {

    public EnigmaMachine createEnigmaPartsAndMachine(CTEEnigma enigmaXML){
        Map<String, Rotor> allRotors = createRotorsFromXML(enigmaXML);
        Map<String, Reflector> allReflectors = createReflectorsFromXML(enigmaXML);
        return new EnigmaMachine(
                allRotors,
                allReflectors,
                enigmaXML.getCTEMachine().getABC().toUpperCase(),
                enigmaXML.getCTEMachine().getRotorsCount());
    }

    private Map<String, Reflector> createReflectorsFromXML(CTEEnigma enigmaXML){
        Map<String, Reflector> allReflectors = new HashMap<>();
        for(CTEReflector reflectorXML : enigmaXML.getCTEMachine().getCTEReflectors().getCTEReflector()){
            List<Reflect> reflectList = new ArrayList<>();
            for(CTEReflect reflect : reflectorXML.getCTEReflect()){
                Reflect reflectToInsert = new Reflect(reflect.getInput(), reflect.getOutput());
                reflectList.add(reflectToInsert);
            }
            Reflector reflectorToInsert = new Reflector(reflectorXML.getId().toUpperCase(), reflectList);
            allReflectors.put(reflectorXML.getId().toUpperCase(), reflectorToInsert);
        }
        return allReflectors;
    }

    private Map<String, Rotor>  createRotorsFromXML(CTEEnigma enigmaXML){
        Map<String, Rotor> rotors = new HashMap<>();
        for(CTERotor rotorXML : enigmaXML.getCTEMachine().getCTERotors().getCTERotor()){
            List<Positioning> positioningList = new ArrayList<>();
            for(CTEPositioning positioning : rotorXML.getCTEPositioning()){
                Positioning positionToInsert = new Positioning(positioning.getLeft().toUpperCase(), positioning.getRight().toUpperCase());
                positioningList.add(positionToInsert);
            }
            Rotor rotorToInsert = new Rotor(Integer.toString(rotorXML.getId()), rotorXML.getNotch(), positioningList);
            rotors.put(Integer.toString(rotorXML.getId()), rotorToInsert);
        }
        return rotors;
    }
}
