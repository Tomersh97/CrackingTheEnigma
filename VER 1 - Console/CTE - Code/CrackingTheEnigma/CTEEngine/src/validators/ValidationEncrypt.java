package validators;

import exception.CharacterNotExistInABC;
import machine.Keyboard;



public class ValidationEncrypt  {

    public void validateEncryptedMessage(String message, Keyboard usedABC){
        int messageSize = message.length();
        for(int i = 0; i < messageSize; i++){
            if(!usedABC.isCharacterInAbc(message.charAt(i))){
                throw new CharacterNotExistInABC(String.valueOf(message.charAt(i)),usedABC.getKeyboardABC());
            }
        }
    }
}
