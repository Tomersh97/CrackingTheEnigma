package machine;

import java.io.Serializable;
import java.util.*;

public class Rotor implements Resetable, Serializable {
    private final String rotorId;
    private int topPos;
    private final int notchPos;
    private final List<Positioning> positioningList = new ArrayList<>();
    private final Map<String,Integer> leftDirToPos = new HashMap<>();
    private final Map<String,Integer> rightDirToPos = new HashMap<>();


    public Rotor(String rotorId, int notchPos, List<Positioning> positions) {
        this.rotorId = rotorId;
        this.notchPos = notchPos - 1;
        int positionInd = 0;
        for(Positioning position : positions){
            leftDirToPos.put(position.getLeft(),positionInd);
            rightDirToPos.put(position.getRight(), positionInd);
            positionInd++;
        }
        positioningList.addAll(positions);
    }

    public String getNotchDistanceFromTop(int sizeABC){
        int distance = 0;
        if(topPos > notchPos){
            distance = sizeABC - (topPos - notchPos);
        }
        else if(topPos < notchPos){
            distance = notchPos - topPos;
        }

        return Integer.toString(distance);
    }

    public int getTopPos() {
        return topPos;
    }

    public String getRotorId(){
        return rotorId;
    }

    public void adjustRotorAtTop(String charAtTop){
        topPos = rightDirToPos.get(charAtTop);
    }

    public String getCharAtTop(int pos){
        return positioningList.get(pos).getRight();
    }

    public boolean increaseTopPos(int size_abc) {
        boolean isRotationFinished = true;
        topPos++;
        if (topPos == size_abc) {
            topPos = 0;
        }
        isRotationFinished = topPos != notchPos;

        return isRotationFinished;
    }

    public int encryptThroughRotor(int entryPos, int abc_size, boolean isMovingLeft){
        String entryChar = getEntryChar(entryPos, abc_size, isMovingLeft);
        return getOutputPos(entryChar, isMovingLeft, abc_size);
    }

    private String getEntryChar(int charPos, int size_abc, boolean isMovingLeft) {
        String entryChar;
        int pos = charPos + topPos;
        if(pos >= size_abc){
            pos = pos % size_abc;
        }

        if (isMovingLeft) {
            entryChar = positioningList.get(pos).getRight();
        } else {
            entryChar = positioningList.get(pos).getLeft();
        }
        return entryChar;
    }

    private int getOutputPos(String entryChar, boolean isMovingLeft, int abc_size){
        int outPos;
        if(isMovingLeft){
            outPos = leftDirToPos.get(entryChar);
        }
        else {
            outPos = rightDirToPos.get(entryChar);
        }

        if(topPos > outPos){
            outPos = abc_size - (topPos - outPos);
        }
        else if(topPos < outPos){
            outPos = outPos - topPos;
        }
        else{
            outPos = 0;
        }
        return outPos;
    }

    @Override
    public String toString() {
        return this.rotorId;
    }

    @Override
    public void reset() {
        positioningList.clear();
        leftDirToPos.clear();
        rightDirToPos.clear();
    }
}