package serializable;

import classes.EngineStats;
import classes.MachineCodeForm;
import machine.EnigmaMachine;

import java.io.Serializable;

public class MachineAndStats implements Serializable {
    private final EnigmaMachine enigmaMachine;
    private final EngineStats engineStats;
    private final MachineCodeForm originalCodeForm;

    public MachineAndStats(EnigmaMachine machine, EngineStats stats, MachineCodeForm codeForm){
        this.enigmaMachine = machine;
        this.engineStats = stats;
        this.originalCodeForm = codeForm;
    }

    public EngineStats getEngineStats() {
        return engineStats;
    }

    public EnigmaMachine getEnigmaMachine() {
        return enigmaMachine;
    }

    public MachineCodeForm getOriginalCodeForm() {
        return originalCodeForm;
    }
}
