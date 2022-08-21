package ui.input;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import dtos.MachineGeneralDetailsDTO;
import dtos.ReflectorDTO;
import dtos.RotorDTO;
import ui.exception.*;
import exception.ParsingToIntegerException;
import ui.menu.MainMenuChoices;
import ui.output.OutputManager;
import utils.Utilities;

public class InputManager {
    private final Scanner inputScanner = new Scanner(System.in);
    private final OutputManager outputManager = new OutputManager();

    public MainMenuChoices getMenuChoice(){
        MainMenuChoices choice = MainMenuChoices.getChoice(0);
        boolean isValidMenuChoice = false;
        String userChoiceStr = "";
        do {
            try {
                userChoiceStr = inputScanner.nextLine();
                int userChoiceInteger = Integer.parseInt(userChoiceStr);
                if(userChoiceInteger > MainMenuChoices.values().length || userChoiceInteger < 1){
                    outputManager.printMessage("Choice is not valid! enter a choice between 1 and " + MainMenuChoices.values().length);
                }
                 else{
                    choice = MainMenuChoices.getChoice(userChoiceInteger);
                    isValidMenuChoice = true;
                }
            }
            catch (NumberFormatException e){
                throw new ParsingToIntegerException(userChoiceStr);
            }

        }while(!isValidMenuChoice);
        return choice;
    }

    public Path getXMLFilePath(){
        Scanner scanner = new Scanner(System.in);
        outputManager.printEmptyLine();
        outputManager.printMessage("Please provide a full path to your XML file");
        String filepath = scanner.nextLine();
        return Paths.get(filepath);
    }

    public String getSaveLoadFilePath(boolean isSave){
        Scanner scanner = new Scanner(System.in);
        if(isSave) {
            outputManager.printMessage("Please enter requested directory to save in.");
        }
        else{
            outputManager.printMessage("Please provide full path with the file name to load.");
        }
        String filePath = scanner.nextLine();
        if(!Files.exists(Paths.get(filePath))){
            throw new FileDirectoryDoesNotExist(filePath);
        }

        return filePath;
    }

    public List<String> getSelectedRotors(RotorDTO rotorDetails){
        List<String> rotors = new ArrayList<>();
        int totalRotors = rotorDetails.getRotorsTotal();
        int rotorsCount = rotorDetails.getRotorsInUse();
        int ind = 0;
        outputManager.printMessage("Choose a total of "+ rotorsCount +" rotors between 1 and " + totalRotors + " and seperate them with ','.");
        String userRotors = new Scanner(System.in).nextLine();
        do{
            StringBuilder toAdd = new StringBuilder();
            while(userRotors.charAt(ind) != ',') {
                toAdd.append(userRotors.charAt(ind));
                ind++;
                if(ind >= userRotors.length()){
                    break;
                }
            }
            rotors.add(toAdd.toString());
            ind++;
        }while(ind < userRotors.length());
        if(userRotors.charAt(userRotors.length() - 1) == ','){
            rotors.add("");
        }

        if(rotors.size() != rotorsCount) {
            throw new InvalidAmountRotorsChosen(rotors.size(), rotorsCount);
        }
        Collections.reverse(rotors);
        return rotors;
    }

    public List<String> getSelectedTopPositions(MachineGeneralDetailsDTO details){
        outputManager.printMessage(
                "Insert slide position for all "+
                details.getRotorsDetails().getRotorsInUse()+
                " rotors, matching the keyboard characters: "+
                details.getKeyboardDetails().getMachineABC());
        String topPositionsStr = inputScanner.nextLine();
        topPositionsStr = topPositionsStr.toUpperCase();
        if(topPositionsStr.length() != details.getRotorsDetails().getRotorsInUse()){
            throw new InvalidAmountTopPositionsChosen(topPositionsStr.length(),details.getRotorsDetails().getRotorsInUse());
        }
        List<String> topPositions = new ArrayList<>();
        for(int i = 0; i < topPositionsStr.length(); i++){
            topPositions.add(String.valueOf(topPositionsStr.charAt(i)));
        }
        Collections.reverse(topPositions);
        return topPositions;
    }

    public List<String> getSelectedReflector(ReflectorDTO reflectorDetails){
        List<String> reflector = new ArrayList<>();
        boolean isValid = false;
        int totalNumReflectors = reflectorDetails.getNumReflectorsTotal();
        do {
            try {
                outputManager.printMessage("Insert the refelector number you wish to choose.");
                for (int i = 1; i <= totalNumReflectors; i++) {
                    outputManager.printMessage(i + ". " + Utilities.convertInteger1to5ToRoman(i));
                }
                String choice = inputScanner.nextLine();
                int reflectorChoice = Integer.parseInt(choice);
                if(reflectorChoice < 1 || reflectorChoice > totalNumReflectors){
                    throw new ReflectorIntegerOutOfBounds(reflectorChoice, totalNumReflectors);
                }
                isValid = true;
                reflector.add(Utilities.convertInteger1to5ToRoman(reflectorChoice));
            }
            catch (NumberFormatException e){
                outputManager.printMessage("Please insert a number representing the reflector you are choosing!");
            }
            catch(ReflectorIntegerOutOfBounds e){
                outputManager.printException(e.getExceptionDetails());
            }
        }while(!isValid);
        return reflector;
    }

    public List<String> getSelectedPlugs(int numPlugsMax){
        List<String> plugs = new ArrayList<>();
        outputManager.printMessage("Insert plugs for the machine, a total of " + numPlugsMax + " plugs can be inserted.");
        outputManager.printMessage("If you wish not to include any plugs, just press 'ENTER'.");
        String plugsString = inputScanner.nextLine();
        plugsString = plugsString.toUpperCase();
        if(plugsString.length() % 2 != 0){
            throw new PlugsChosenInvalidSize(plugsString);
        }
        if(plugsString.length() == 0){
            return plugs;
        }
        else{
            String[] delimitedPlugs = plugsString.split("(?<=\\G.{2})");
            plugs.addAll(Arrays.asList(delimitedPlugs));
        }
        return plugs;
    }

    public String getMessageToEncrypt(String ABC){
        outputManager.printMessage("Please insert a message to encrypt:");
        outputManager.printMessage("Make sure the message is a a part of the ABC: " + ABC + ".");
        String toEncrpyt = inputScanner.nextLine();
        toEncrpyt = toEncrpyt.toUpperCase();
        return toEncrpyt;
    }
}
