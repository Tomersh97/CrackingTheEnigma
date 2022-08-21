package machine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Keyboard implements Resetable, Serializable {

    private String keyboardABC;
    private final Map<Character, Integer> charToIndex = new HashMap<>();
    private int keyboardSize;

    public Keyboard(String abc){
        keyboardABC = abc;
        keyboardSize = abc.length();
        for(int i = 0; i < keyboardSize; i++){
            charToIndex.put(keyboardABC.charAt(i), i);
        }
    }

    @Override
    public void reset() {
        keyboardABC = "";
        charToIndex.clear();
        keyboardSize = 0;
    }

    public String getKeyboardABC() {
        return keyboardABC;
    }

    public boolean isCharacterInAbc(Character givenChar){
        return charToIndex.containsKey(givenChar);
    }

    public int getCharToEntryMapping(String charInKeyboard){
       return charToIndex.get(charInKeyboard.charAt(0));
    }

    public String getIndexToCharMapping(Integer index){
        return keyboardABC.substring(index, index + 1);
    }

    public int getKeyboardSize(){
        return keyboardSize;
    }
}