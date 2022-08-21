package interfaces.general;

import classes.EngineStats;
import classes.MachineCodeForm;
import classes.SetForUseChoices;
import dtos.EncryptionStatsDTO;
import dtos.LoadedMachineDTO;
import dtos.MachineDetailsDTO;
import dtos.SaveFileDTO;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public interface EnigmaOperable extends Resetable {
    void createMachineFromXML(Path xmlFilePath);
    MachineDetailsDTO getMachineDetails();
    void setMachineForUse(List<String> choices, SetForUseChoices choiceType);
    MachineCodeForm randomizeSetMachine();
    EncryptionStatsDTO encryptAndRecordEncryption(EncryptionStatsDTO beforeEncryptDTO);

    SaveFileDTO serializeMachineAndStats(String filepath)throws IOException;

    LoadedMachineDTO deserializeMachineAndStats(String filepath)throws IOException, ClassNotFoundException;

    boolean getIsMachineLoadedFromXML();

    void resetLoadedMachine();

    EngineStats getMachineStats();

    boolean getIsMachineUsable();
}
