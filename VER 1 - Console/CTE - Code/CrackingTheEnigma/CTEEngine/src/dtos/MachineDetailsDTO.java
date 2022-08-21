package dtos;

public class MachineDetailsDTO {

    private MachineInUseDTO inUseDetails;
    private final MachineGeneralDetailsDTO generalDetails;
    boolean isMachineUsable;


    public MachineDetailsDTO(MachineGeneralDetailsDTO details, boolean isMachineReady){
        this.generalDetails = details;
        this.isMachineUsable = isMachineReady;
    }

    public void setInUsedDetails(MachineInUseDTO inUseDetails){
        this.inUseDetails = inUseDetails;
    }

    public boolean getIsMachineUsable(){
        return isMachineUsable;
    }

    public MachineInUseDTO getInUseDetails() {
        return inUseDetails;
    }

    public MachineGeneralDetailsDTO getGeneralDetails(){
        return generalDetails;
    }
}
