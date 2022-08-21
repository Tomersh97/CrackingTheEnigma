package classes;

import interfaces.general.Resetable;
import machine.EnigmaMachine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MachineCodeForm implements Resetable, Serializable {
    private final List<String> rotorsInUseByOrder = new ArrayList<>();
    private final List<String> notchesDistanceFromTop = new ArrayList<>();
    private final List<String> rotorsTopPositionsInOrder = new ArrayList<>();
    private String chosenReflector;
    private final List<String> chosenPlugs = new ArrayList<>();


    public void create(List<String> rotors, List<String> notchesFromTop, List<String> rotorsTopPos, String reflector, List<String> plugs){
        reset();
        setRotorsInUseByOrder(rotors);
        setRotorsTopPositionsInOrder(rotorsTopPos);
        setNotchesDistanceFromTop(notchesFromTop);
        this.chosenReflector = reflector;
        setChosenPlugs(plugs);
    }

    private void setNotchesDistanceFromTop(List<String> notchesFromTopDis){
        this.notchesDistanceFromTop.addAll(notchesFromTopDis);
    }
    private void setChosenPlugs(List<String> plugs){
        this.chosenPlugs.addAll(plugs);
    }

    public List<String> getOriginalStaringTopPositions(){
        return this.rotorsTopPositionsInOrder;
    }

    private void setRotorsTopPositionsInOrder(List<String> rotorsTopPositions){
        this.rotorsTopPositionsInOrder.addAll(rotorsTopPositions);
    }

    private void setRotorsInUseByOrder(List<String> rotors){
        this.rotorsInUseByOrder.addAll(rotors);
    }

    @Override
    public void reset() {
        this.rotorsInUseByOrder.clear();
        this.chosenPlugs.clear();
        this.notchesDistanceFromTop.clear();
        this.rotorsTopPositionsInOrder.clear();
    }

    public static MachineCodeForm updateCodeForm(EnigmaMachine enigmaMachine) {
        MachineCodeForm codeForm = new MachineCodeForm();
        codeForm.create(
                enigmaMachine.getRotorsInOrder(),
                enigmaMachine.getNotchesFromTopDistance(),
                enigmaMachine.getRotorsTopPositionsInOrder(),
                enigmaMachine.getUsedReflectorId(),
                enigmaMachine.getPlugs());
        return codeForm;
    }

    private String getRotorsAndNotchesDisInOrderMessage(){
        StringBuilder rotorsAndNotches = new StringBuilder();
        int numRotors = rotorsInUseByOrder.size();
        for(int i = numRotors - 1; i >= 0; i--){
            if(i !=numRotors - 1){
                rotorsAndNotches.append(",");
            }
            rotorsAndNotches.append(rotorsInUseByOrder.get(i));
            rotorsAndNotches.append("(").append(notchesDistanceFromTop.get(i)).append(")");
        }

        return rotorsAndNotches.toString();
    }

    private String getRotorTopPositionMessage(){
        StringBuilder topPositions = new StringBuilder();
        int numTopPositions = rotorsTopPositionsInOrder.size();
        for(int i = numTopPositions - 1; i >= 0; i--){
            topPositions.append(rotorsTopPositionsInOrder.get(i));
        }
        return topPositions.toString();
    }

    private String getPlugsMessage(){
        StringBuilder plugs = new StringBuilder();
        int numPlugs = chosenPlugs.size();
        for(int i = 0; i < numPlugs; i++){
            if(i != 0){
                plugs.append(",");
            }
            plugs.append(chosenPlugs.get(i).charAt(0));
            plugs.append("|");
            plugs.append(chosenPlugs.get(i).charAt(1));
        }
        return plugs.toString();
    }

    @Override
    public String toString() {
        StringBuilder codeForm = new StringBuilder();
        codeForm.append("<");
        codeForm.append(getRotorsAndNotchesDisInOrderMessage());
        codeForm.append(">");

        codeForm.append("<");
        codeForm.append(getRotorTopPositionMessage());
        codeForm.append(">");

        codeForm.append("<");
        codeForm.append(chosenReflector);
        codeForm.append(">");

        if(!chosenPlugs.isEmpty()){
            codeForm.append("<");
            codeForm.append(getPlugsMessage());
            codeForm.append(">");
        }
        return codeForm.toString();
    }
}
