package ui.output;

import classes.MachineCodeForm;
import dtos.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OutputManager {

    public void showMachineRotorDetails(RotorDTO rotorDetails){
        System.out.println(
                "Rotor details (InUse/Total): " +
                rotorDetails.getRotorsInUse() +
                "/" +
                rotorDetails.getRotorsTotal() +
                ".");
    }

    public void showMachineReflectorDetails(ReflectorDTO reflectorDetails){
        System.out.println("There is a total of " + reflectorDetails.getNumReflectorsTotal() + " reflectors.");
    }

    public void showAmountOfEncryptedMessages(MachineGeneralDetailsDTO details){
        System.out.println("A total of " + details.getEncryptedCount() + " messages have been encrypted.");
    }

    public void showCodeFormations(MachineDetailsDTO machineDetails){
        if(machineDetails.getIsMachineUsable()){
            System.out.println("Original code formation: " + machineDetails.getInUseDetails().getOriginalMachineFormation());
            System.out.println("Current code formation: "+ machineDetails.getInUseDetails().getCurrentMachineFormation());
        }
        else{
            System.out.println("Machine has not been set-up yet! Please use commands 3 or 4 to set up the machine " +
                    "and get the details about the machine in use");
        }
    }

    public void showLoadedFileAndCodeForm(LoadedMachineDTO loadedMachineDTO, String filePath){
        System.out.println("File: " + filePath);
        System.out.println("Was loaded successfully with code formation: " + loadedMachineDTO.getCurrentCodeForm());
        System.out.println("This machine's original code form is: " + loadedMachineDTO.getOriginalCodeForm());
    }

    public void showSaveFileDetails(SaveFileDTO details){
        System.out.println();
        System.out.println("Machine and stats were saved at path: " + details.getFullFilePath());
        System.out.println("File name is: " + details.getFileName());
        System.out.println("Machine was saved under- " + details.getSavedCurrentForm() + " code formation");
        System.out.println("Machine's original code form is- " + details.getSavedOriginalForm());
    }

    public void showEncryptionMade(EncryptionStatsDTO encryption){
        System.out.println();
        System.out.println("Encryption was successful!");
        System.out.println("Message to encrypt was: "+ encryption.getBeforeEncrypt());
        System.out.println("Decrypted message: " + encryption.getAfterEncrypt());
        System.out.println();
    }


    public void showHistoryForSingleCodeFormation(Map.Entry<String, List<EncryptionStatsDTO>> stats){
        System.out.println();
        System.out.println(stats.getKey());
        if(stats.getValue().size() == 0){
            System.out.println("No encryptions made for this code formation.");
        }
        stats.getValue().forEach(System.out::println);
        System.out.println();
    }

    public void showRandomlyChosenMachine(MachineCodeForm codeForm){
        System.out.println();
        System.out.println("The random chosen machine code:");
        System.out.println(codeForm);
        System.out.println();
    }

    public void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printException(ExceptionDTO exceptionDTO){
        System.out.println(exceptionDTO);
    }

    public void printEmptyLine(){
        System.out.println();
    }
}
