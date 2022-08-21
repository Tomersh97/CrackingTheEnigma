package dtos;
import classes.MachineCodeForm;

public class MachineInUseDTO {
    private final MachineCodeForm originalMachineFormation;
    private final MachineCodeForm currentMachineFormation;

    public MachineInUseDTO(MachineCodeForm originalMachineFormation, MachineCodeForm currentMachineFormation){
        this.originalMachineFormation = originalMachineFormation;
        this.currentMachineFormation = currentMachineFormation;
    }

    public MachineCodeForm getOriginalMachineFormation() {
        return originalMachineFormation;
    }

    public MachineCodeForm getCurrentMachineFormation() {
        return currentMachineFormation;
    }
}
