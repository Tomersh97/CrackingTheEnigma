package dtos;

public class RotorDTO {
    private final int rotorsTotal;
    private final int rotorsInUse;

    public RotorDTO(int rotorsTotal, int rotorsInUse){
        this.rotorsTotal = rotorsTotal;
        this.rotorsInUse = rotorsInUse;
    }

    public int getRotorsTotal() {
        return rotorsTotal;
    }

    public int getRotorsInUse(){
        return rotorsInUse;
    }
}
