package dtos;

public class ReflectorDTO {
    private final int numReflectorsTotal;

    public ReflectorDTO(int numReflectorsTotal){
        this.numReflectorsTotal = numReflectorsTotal;
    }

    public int getNumReflectorsTotal() {
        return numReflectorsTotal;
    }
}
