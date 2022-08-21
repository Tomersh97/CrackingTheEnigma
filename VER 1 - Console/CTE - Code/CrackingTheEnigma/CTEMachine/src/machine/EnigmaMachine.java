package machine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnigmaMachine implements Resetable, Serializable {
    private final int totalNumOfRotors;
    private final int rotorsInUseCount;
    private final Map<String, Rotor> allRotors = new HashMap<>();
    private final Map<String, Reflector> allReflectors = new HashMap<>();
    private Reflector inUseReflector;
    private final List<Rotor> usedRotors = new ArrayList<>();
    private final Map<String, String> allPlugs = new HashMap<>();
    private final Keyboard usedKeyboard;
    private boolean isReadyForUse = false;

    @Override
    public void reset() {
        isReadyForUse = false;
        usedKeyboard.reset();
        allPlugs.clear();
        usedRotors.forEach(machine.Rotor::reset);
        usedRotors.clear();
        allRotors.clear();
        allReflectors.clear();
    }

    public void resetCurrentSettings(){
        isReadyForUse = false;
        allPlugs.clear();
        usedRotors.clear();
    }

    public void setIsMachineUsable(boolean isUsable){
        this.isReadyForUse = isUsable;
    }

    public Map<String, Rotor> getAllRotors() {
        return allRotors;
    }

    public boolean isMachineReadyForUse(){
        return isReadyForUse;
    }

    public int getTotalNumOfRotors(){
        return totalNumOfRotors;
    }

    public int getTotalNumOfReflectors(){
        return allReflectors.size();
    }

    public Keyboard getUsedKeyboard(){
        return usedKeyboard;
    }

    public void resetMachineToOriginal(List<String> originalTopPositions){
        int rotorInd = 0;
        for(String topPos : originalTopPositions){
            setRotorToTop(rotorInd, topPos);
            rotorInd++;
        }
    }

    public List<String> getPlugs(){
        List<String> plugs= new ArrayList<>();
        Map<String,String> checkIfInserted = new HashMap<>();
        for(Map.Entry<String, String> entry : allPlugs.entrySet()){
            if(!checkIfInserted.containsKey(entry.getValue())){
                plugs.add(entry.getKey() + entry.getValue());
                checkIfInserted.put(entry.getKey(), entry.getValue());
            }
        }
        return plugs;
    }

    public int getNumOfRotorsInUse(){
        return this.rotorsInUseCount;
    }

    public String getUsedReflectorId(){
        return inUseReflector.getReflectorId();
    }

    public List<String> getRotorsInOrder(){
        List<String> rotorsInOrder = new ArrayList<>();

        for(Rotor rotor : usedRotors){
            rotorsInOrder.add(rotor.getRotorId());
        }
        return rotorsInOrder;
    }

    public List<String> getRotorsTopPositionsInOrder(){
        List<String> rotorPositionsInOrder = new ArrayList<>();

        for(Rotor rotor : usedRotors){
            int rotorTopPos = rotor.getTopPos();
            rotorPositionsInOrder.add(rotor.getCharAtTop(rotorTopPos));
        }

        return rotorPositionsInOrder;
    }

    public EnigmaMachine(Map<String, Rotor> rotors, Map<String, Reflector> reflectors, String abc, int rotorsCount){
        this.rotorsInUseCount = rotorsCount;
        this.allRotors.putAll(rotors);
        this.allReflectors.putAll(reflectors);
        this.usedKeyboard = new Keyboard(abc);
        this.totalNumOfRotors = allRotors.size();
    }

    public String encryptCode(String code){
       StringBuilder encrypted = new StringBuilder();
       int code_size = code.length();
       for(int i = 0; i < code_size; i++){
           encrypted.append(getEncryptedChar(Character.toString(code.charAt(i))));
       }

       return encrypted.toString();
    }

    public void setUsedReflector(String reflectorId){
        inUseReflector = allReflectors.get(reflectorId);
    }

    public void setPlug(String input, String output){
        allPlugs.put(input, output);
        allPlugs.put(output, input);
    }

    public void setUsedRotor(String rotorId){
        usedRotors.add(allRotors.get(rotorId));
    }

    public void setRotorToTop(int rotorInd, String charAtTop){
        usedRotors.get(rotorInd).adjustRotorAtTop(charAtTop);
    }

    public String getEncryptedChar(String doEncrypt){
        boolean movingLeft = true;
        String encryptedChar;
        rotateRotors();
        if(isPlugged(doEncrypt)){
            doEncrypt = allPlugs.get(doEncrypt);
        }
        int entryIndex = usedKeyboard.getCharToEntryMapping(doEncrypt);

        for(int i = 0; i < rotorsInUseCount; i++){
            entryIndex = usedRotors.get(i).encryptThroughRotor(entryIndex, usedKeyboard.getKeyboardSize(), movingLeft);
        }
        entryIndex = inUseReflector.getReflection(entryIndex);
        for(int i = rotorsInUseCount - 1; i >= 0; i--){
            entryIndex = usedRotors.get(i).encryptThroughRotor(entryIndex, usedKeyboard.getKeyboardSize(), !movingLeft);
        }

        encryptedChar = usedKeyboard.getIndexToCharMapping(entryIndex);
        if(isPlugged(encryptedChar)){
            encryptedChar = allPlugs.get(encryptedChar);
        }

        return encryptedChar;
    }

    public List<String> getNotchesFromTopDistance(){
        List<String> notchFromTopDistances = new ArrayList<>();
        for(Rotor rotor : usedRotors){
            notchFromTopDistances.add(rotor.getNotchDistanceFromTop(usedKeyboard.getKeyboardSize()));
        }

        return notchFromTopDistances;
    }

    private void rotateRotors(){
        boolean rotationFinish = false;
        int rotorInd = 0;
        while(!rotationFinish && rotorInd != usedRotors.size()){
            rotationFinish = usedRotors.get(rotorInd).increaseTopPos(usedKeyboard.getKeyboardSize());
            rotorInd++;
        }
    }

    private boolean isPlugged(String checkChar){
        return allPlugs.containsKey(checkChar);
    }
}
