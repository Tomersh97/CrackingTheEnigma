package ui;

import classes.*;
import dtos.EncryptionStatsDTO;
import dtos.LoadedMachineDTO;
import dtos.MachineDetailsDTO;
import dtos.SaveFileDTO;
import exception.EnigmaEngineException;
import interfaces.general.EnigmaOperable;
import ui.exception.EnigmaUIException;
import exception.MachineNotLoadedException;
import exception.MachineNotSetUsable;
import ui.menu.MainMenuChoices;
import ui.menu.MenuItem;
import ui.input.InputManager;
import ui.output.OutputManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EngimaConsoleUI implements EnigmaUIRunnable{
    private final EnigmaOperable enigmaEngine = new Engine();
    private final MenuItem mainMenuItem;
    private boolean isRunning = true;
    private final InputManager inputManager = new InputManager();
    private final OutputManager outputManager = new OutputManager();
    private String currentLoadedPathString;

    public EngimaConsoleUI(){
        currentLoadedPathString = "No machine had been loaded yet";
        this.mainMenuItem =
                new MenuItem("Choose any of the following options to operate on the Enigma Machine!",
                    new MenuItem("1. Load machine from xml file",
                        new MenuItem("Please provide full path ending with .xml")),
                    new MenuItem("2. Get loaded machine details"),
                    new MenuItem("3. Set machine for use - manually",
                        new MenuItem("Insert rotors"),
                        new MenuItem("Insert rotors starting positions"),
                        new MenuItem("Choose reflector"),
                        new MenuItem("Insert plugs")),
                    new MenuItem("4. Set machine for use - randomly"),
                    new MenuItem("5. Encrypt code",
                        new MenuItem("Please provide characters only from the given ABC")),
                    new MenuItem("6. Reset machine's rotors to starting positions"),
                    new MenuItem("7. Show encryption history for all machines set for this XML"),
                    new MenuItem("8. Save current machine",
                        new MenuItem("Please provide a full path to save the machine in")),
                    new MenuItem("9. Load saved machine",
                        new MenuItem("Please provide a full path to load a machine from")),
                    new MenuItem("10. Exit program"));
    }

    @Override
    public void runEnigma() {
        while(isRunning){
            mainMenuItem.showMenu();
            outputManager.printMessage("Current loaded machine: " + currentLoadedPathString);
            try {
                getAndPerformAction();
            } catch (EnigmaEngineException e){
                outputManager.printException(e.getExceptionDetails());
            }
            catch (EnigmaUIException e) {
                outputManager.printException(e.getExceptionDetails());
            } catch (Exception e){
                outputManager.printMessage(e.getMessage());
            }
            outputManager.promptEnterKey();
        }
    }

    private void getAndShowMachineDetails(MachineDetailsDTO details) {
        if(!enigmaEngine.getIsMachineLoadedFromXML()){
            throw new MachineNotLoadedException();
        }
        System.out.println();
        outputManager.showMachineRotorDetails(details.getGeneralDetails().getRotorsDetails());
        outputManager.showMachineReflectorDetails(details.getGeneralDetails().getReflectorsDetails());
        outputManager.showAmountOfEncryptedMessages(details.getGeneralDetails());
        outputManager.showCodeFormations(details);
        System.out.println();
    }

    private void getAndEncryptMessage(MachineDetailsDTO details) {
        if (!enigmaEngine.getIsMachineLoadedFromXML()) {
            throw new MachineNotLoadedException();
        }
        if (!enigmaEngine.getIsMachineUsable()) {
            throw new MachineNotSetUsable();
        }

        String toEncrypt = inputManager.getMessageToEncrypt(details.getGeneralDetails().getKeyboardDetails().getMachineABC());
        EncryptionStatsDTO decrypted = enigmaEngine.encryptAndRecordEncryption(new EncryptionStatsDTO(toEncrypt));
        outputManager.showEncryptionMade(decrypted);
    }

    private void performMachineReset(){
        enigmaEngine.reset();
        outputManager.printMessage("Machine was reset to starting position.");
    }

    private void getAndShowMachineStats(){
        EngineStats machineStats = enigmaEngine.getMachineStats();
        if(machineStats.getFormationToEncryptionStats().size() == 0){
            outputManager.printMessage("Nothing to show. Encrypt at least one message to view details.");
        }
        for(Map.Entry<String, List<EncryptionStatsDTO>> stats: machineStats.getFormationToEncryptionStats().entrySet()){
            outputManager.showHistoryForSingleCodeFormation(stats);
        }
    }

    private void getXMLFileAndCreateMachine() {
        Path filepath = inputManager.getXMLFilePath();
        enigmaEngine.createMachineFromXML(filepath);
        currentLoadedPathString = filepath.getFileName().toString();

        outputManager.printMessage("File " + currentLoadedPathString + " was loaded successfuly!");
        outputManager.printEmptyLine();
    }

    private void showAndRandomizeMachineForUse(){
        MachineCodeForm codeForm = enigmaEngine.randomizeSetMachine();
        outputManager.showRandomlyChosenMachine(codeForm);
    }

    private void getAndPerformAction(){
        MainMenuChoices userChoice = inputManager.getMenuChoice();
        MachineDetailsDTO details = enigmaEngine.getMachineDetails();
        switch (userChoice){
            case LOADMACHINEXML:
                getXMLFileAndCreateMachine();
                break;
            case SHOWMACHINEDETAILS:
                getAndShowMachineDetails(details);
                break;
            case SETMACHINE:
                getMachineDetailsToSet(details);
                break;
            case SETRANDOMMACHINE:
                showAndRandomizeMachineForUse();
                break;
            case ENCRYPT:
                getAndEncryptMessage(details);
                break;
            case RESETMACHINE:
                performMachineReset();
                break;
            case SHOWSTATS:
                getAndShowMachineStats();
                break;
            case SAVEMACHINE:
                saveCurrentMachine();
                break;
            case LOADMACHINE:
                loadExistingMachine();
                break;
            case EXIT:
                isRunning = false;
                break;
        }
    }

    private void loadExistingMachine(){
            try{
                String loadPath = inputManager.getSaveLoadFilePath(false);
                LoadedMachineDTO loadedMachineDTO = enigmaEngine.deserializeMachineAndStats(loadPath);
                outputManager.showLoadedFileAndCodeForm(loadedMachineDTO, loadPath);
                currentLoadedPathString = loadPath;
            }
            catch (IOException | ClassNotFoundException e){
                outputManager.printEmptyLine();
                outputManager.printMessage("Failed to load file, please try again");
                outputManager.printEmptyLine();
            } catch(EnigmaUIException e){
                outputManager.printException(e.getExceptionDetails());
            }
    }

    private void sendChoiceToValidateAndSet(List<String> chosenToSet,SetForUseChoices setType) {
            enigmaEngine.setMachineForUse(chosenToSet, setType);
    }

    private void saveCurrentMachine() {
        if (!enigmaEngine.getIsMachineLoadedFromXML()) {
            throw new MachineNotLoadedException();
        }
        if (!enigmaEngine.getIsMachineUsable()) {
            throw new MachineNotSetUsable();
        }
        try {
            String savePath = inputManager.getSaveLoadFilePath(true);
            SaveFileDTO saveDetails = enigmaEngine.serializeMachineAndStats(savePath);
            outputManager.showSaveFileDetails(saveDetails);


        } catch (IOException e) {
            outputManager.printEmptyLine();
            outputManager.printMessage("Failed to save file, please try again");
            outputManager.printEmptyLine();
        } catch (EnigmaUIException e) {
            outputManager.printException(e.getExceptionDetails());
        }
    }

    private void getMachineDetailsToSet(MachineDetailsDTO details) {
        if (!enigmaEngine.getIsMachineLoadedFromXML()) {
            throw new MachineNotLoadedException();
        }
        enigmaEngine.resetLoadedMachine();
        boolean isAllSet = false;
        List<String> chosenToSet = new ArrayList<>();
        SetForUseChoices setType = SetForUseChoices.ROTOR;
            do {
                try {
                    switch (setType) {
                        case ROTOR:
                            chosenToSet = inputManager.getSelectedRotors(details.getGeneralDetails().getRotorsDetails());
                            break;
                        case ROTORSLIDEPOS:
                            chosenToSet = inputManager.getSelectedTopPositions(details.getGeneralDetails());
                            break;
                        case REFLECTOR:
                            chosenToSet = inputManager.getSelectedReflector(details.getGeneralDetails().getReflectorsDetails());
                            break;
                        case PLUG:
                            chosenToSet = inputManager.getSelectedPlugs(details.getGeneralDetails().getKeyboardDetails().getSizeABC() / 2);
                            break;
                        case ALLSET:
                            chosenToSet.clear();
                            chosenToSet.add("");
                            isAllSet = true;
                            break;
                    }
                    sendChoiceToValidateAndSet(chosenToSet, setType);
                    setType.reset();
                    setType = setType.setToNext();
                }

                catch (EnigmaEngineException e) {
                    outputManager.printException(e.getExceptionDetails());
                } catch (EnigmaUIException e) {
                    outputManager.printException(e.getExceptionDetails());
                }
            } while (!isAllSet);
        outputManager.printMessage("Machine is now usable!.");
    }

}
