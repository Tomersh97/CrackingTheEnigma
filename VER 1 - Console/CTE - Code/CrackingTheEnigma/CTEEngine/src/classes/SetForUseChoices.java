package classes;

import interfaces.general.Resetable;

public enum SetForUseChoices implements Resetable {
    ROTOR(),
    ROTORSLIDEPOS(0),
    REFLECTOR,
    PLUG,
    ALLSET;

    private int rotorInd;

    SetForUseChoices(){}

    SetForUseChoices(int rotorInd){
        this.rotorInd = rotorInd;
    }


    public int getRotorInd(){
        return this.rotorInd;
    }

    public void setNextRotorInd(){
        rotorInd++;
    }
    @Override
    public void reset() {
        rotorInd = 0;
    }


    public SetForUseChoices setToNext(){
        SetForUseChoices useChoice = ROTOR;
        switch (this){
            case ROTOR:
                useChoice = ROTORSLIDEPOS;
                break;
            case ROTORSLIDEPOS:
                useChoice = REFLECTOR;
                break;
            case REFLECTOR:
                useChoice =PLUG;
                break;
            case PLUG:
                useChoice = ALLSET;
                break;
        }
        return useChoice;
    }
}
