package dtos;

public class KeyboardDTO {
    private final String machineABC;
    private final int sizeABC;

    public KeyboardDTO(String machineABC, int sizeABC){
        this.machineABC = machineABC;
        this.sizeABC = sizeABC;
    }

    public String getMachineABC() {
        return machineABC;
    }

    public int getSizeABC() {
        return sizeABC;
    }
}
