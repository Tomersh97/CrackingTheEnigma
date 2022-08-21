package dtos;

import classes.MachineCodeForm;

public class LoadedMachineDTO {
    private final MachineCodeForm currentCodeForm;
    private final MachineCodeForm originalCodeForm;

    public LoadedMachineDTO(MachineCodeForm currentCodeForm, MachineCodeForm originalCodeForm) {
        this.currentCodeForm = currentCodeForm;
        this.originalCodeForm = originalCodeForm;
    }

    public MachineCodeForm getCurrentCodeForm() {
        return currentCodeForm;
    }

    public MachineCodeForm getOriginalCodeForm() {
        return originalCodeForm;
    }
}
