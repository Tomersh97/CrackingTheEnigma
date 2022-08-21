package dtos;

import classes.MachineCodeForm;

public class SaveFileDTO {
    private final String fullFilePath;
    private final String fileName;
    private final MachineCodeForm savedCurrentForm;
    private final MachineCodeForm savedOriginalForm;

    public SaveFileDTO(String fullFilePath, String fileName, MachineCodeForm savedCurrentForm, MachineCodeForm savedOriginalForm){
        this.fullFilePath = fullFilePath;
        this.fileName = fileName;
        this.savedCurrentForm = savedCurrentForm;
        this.savedOriginalForm = savedOriginalForm;
    }

    public String getFullFilePath() {
        return fullFilePath;
    }

    public String getFileName() {
        return fileName;
    }

    public MachineCodeForm getSavedOriginalForm() {
        return savedOriginalForm;
    }

    public MachineCodeForm getSavedCurrentForm() {
        return savedCurrentForm;
    }
}
