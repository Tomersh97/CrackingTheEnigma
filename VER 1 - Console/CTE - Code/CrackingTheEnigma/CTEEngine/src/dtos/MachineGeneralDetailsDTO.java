package dtos;


public class MachineGeneralDetailsDTO {
    private final KeyboardDTO keyboardDetails;
    private final RotorDTO rotorsDetails;
    private final ReflectorDTO reflectorsDetails;
    private final int encryptedCount;

    public MachineGeneralDetailsDTO(RotorDTO rotorsDetails, ReflectorDTO reflectorsDetails,KeyboardDTO keyboardDetails, int encrypted){
        this.rotorsDetails = rotorsDetails;
        this.reflectorsDetails = reflectorsDetails;
        this.keyboardDetails = keyboardDetails;
        this.encryptedCount = encrypted;

    }

    public KeyboardDTO getKeyboardDetails() {
        return keyboardDetails;
    }

    public RotorDTO getRotorsDetails() {
        return rotorsDetails;
    }

    public ReflectorDTO getReflectorsDetails() {
        return reflectorsDetails;
    }

    public int getEncryptedCount() {
        return encryptedCount;
    }
}
