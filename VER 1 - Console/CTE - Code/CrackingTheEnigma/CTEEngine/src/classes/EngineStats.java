package classes;

import dtos.EncryptionStatsDTO;
import interfaces.general.Resetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineStats implements Resetable, Serializable {
    private final Map<String, List<EncryptionStatsDTO>> formationToEncryptionStats = new HashMap<>();
    private int numEncrypted = 0;

    public int getNumEncrypted() {
        return numEncrypted;
    }

    public Map<String, List<EncryptionStatsDTO>> getFormationToEncryptionStats(){
        return formationToEncryptionStats;
    }

    public void reset(){
        formationToEncryptionStats.clear();
        numEncrypted = 0;
    }


    public void insertEncryptedEntry(MachineCodeForm codeFormation, EncryptionStatsDTO encryption){
        if(formationToEncryptionStats.containsKey(codeFormation.toString())){
            formationToEncryptionStats.get(codeFormation.toString()).add(encryption);
        }
        else{
            List<EncryptionStatsDTO> encryptionForCode = new ArrayList<>();
            encryptionForCode.add(encryption);
            formationToEncryptionStats.put(codeFormation.toString(), encryptionForCode);
        }
        numEncrypted ++;
    }

}
