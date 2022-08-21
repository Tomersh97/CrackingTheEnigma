package ui.menu;

import java.util.HashMap;
import java.util.Map;

public enum MainMenuChoices {
    LOADMACHINEXML(1),
    SHOWMACHINEDETAILS(2),
    SETMACHINE(3),
    SETRANDOMMACHINE(4),
    ENCRYPT(5),
    RESETMACHINE(6),
    SHOWSTATS(7),
    SAVEMACHINE(8),
    LOADMACHINE(9),
    EXIT(10);

    private final int choiceId;
    private final static Map<Integer, MainMenuChoices> idToChoice = new HashMap<>();


    MainMenuChoices(int choiceId){
        this.choiceId = choiceId;

    }
    static {
        for(MainMenuChoices mainMenuChoices : MainMenuChoices.values()){
            idToChoice.put(mainMenuChoices.choiceId, mainMenuChoices);
        }
    }

    public static MainMenuChoices getChoice(Integer choice){
        return idToChoice.get(choice);
    }
}
