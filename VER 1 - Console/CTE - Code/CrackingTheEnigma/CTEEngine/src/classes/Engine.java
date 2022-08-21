package classes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import dtos.*;
import exception.FileDoesNotEndAsXML;
import exception.FileNotExistException;
import exception.MachineNotLoadedException;
import exception.MachineNotSetUsable;
import interfaces.general.EnigmaOperable;
import machine.*;
import resources.schemas.generated.*;

import serializable.MachineAndStats;
import validators.ValidationManager;


public class Engine implements EnigmaOperable {
    private EnigmaMachine enigmaMachine;
    private final ValidationManager validationManager = new ValidationManager();
    private EngineStats engineStats = new EngineStats();
    private final static String JAXB_XML_ENIGMA_NAME = "resources.schemas.generated";
    private MachineCodeForm originalCodeFormation = new MachineCodeForm();
    private boolean isMachineLoaded = false;
    private final EnigmaRandomizer randomizer = new EnigmaRandomizer();
    private final MachinePartCreator enigmaCreator = new MachinePartCreator();

    @Override
    public boolean getIsMachineLoadedFromXML() {
        return isMachineLoaded;
    }

    @Override
    public SaveFileDTO serializeMachineAndStats(String filepath) throws IOException {
        String saveName;
        Date date = new Date();
        SimpleDateFormat now = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateAndTimeNow = now.format(date).replace(":", " ");
        saveName = "save_" + dateAndTimeNow;
        filepath += "\\" + saveName;
        MachineAndStats toSave = new MachineAndStats(enigmaMachine, engineStats, originalCodeFormation);
        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(toSave);
        objectOutputStream.flush();
        objectOutputStream.close();
        MachineCodeForm currentForm = MachineCodeForm.updateCodeForm(enigmaMachine);
        return new SaveFileDTO(filepath, saveName, currentForm, originalCodeFormation);
    }

    @Override
    public LoadedMachineDTO deserializeMachineAndStats(String filepath)
            throws IOException, ClassNotFoundException  {
        FileInputStream fileInputStream = new FileInputStream(filepath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        MachineAndStats loadedMachine = (MachineAndStats) objectInputStream.readObject();
        enigmaMachine = loadedMachine.getEnigmaMachine();
        engineStats = loadedMachine.getEngineStats();
        originalCodeFormation = loadedMachine.getOriginalCodeForm();
        objectInputStream.close();
        isMachineLoaded = true;
        MachineCodeForm currentLoadedForm = MachineCodeForm.updateCodeForm(enigmaMachine);
        return new LoadedMachineDTO(currentLoadedForm, originalCodeFormation);
    }

    @Override
    public boolean getIsMachineUsable() {
        return enigmaMachine.isMachineReadyForUse();
    }

    @Override
    public void reset() {
        if(!isMachineLoaded){
            throw new MachineNotLoadedException();
        }
        if(!enigmaMachine.isMachineReadyForUse()){
            throw new MachineNotSetUsable();
        }
        enigmaMachine.resetMachineToOriginal(originalCodeFormation.getOriginalStaringTopPositions());
    }

    @Override
    public void resetLoadedMachine() {
        enigmaMachine.resetCurrentSettings();
    }

    @Override
    public EngineStats getMachineStats() {
        if(!isMachineLoaded){
            throw new MachineNotLoadedException();
        }
        if(!getIsMachineUsable()){
            throw new MachineNotSetUsable();
        }
        return engineStats;
    }

    @Override
    public EncryptionStatsDTO encryptAndRecordEncryption(EncryptionStatsDTO beforeEncryptDTO) {
        long start = System.nanoTime();
        validationManager.validateEncryption(beforeEncryptDTO.getBeforeEncrypt(), enigmaMachine.getUsedKeyboard());
        String encrypted = enigmaMachine.encryptCode(beforeEncryptDTO.getBeforeEncrypt());
        long finish = System.nanoTime();
        long elapsedInNano = finish - start;
        EncryptionStatsDTO afterEncrypted = new EncryptionStatsDTO(beforeEncryptDTO.getBeforeEncrypt(), encrypted, elapsedInNano);
        engineStats.insertEncryptedEntry(originalCodeFormation, afterEncrypted);
        return afterEncrypted;
    }

    @Override
    public MachineCodeForm randomizeSetMachine() {
        if(!isMachineLoaded){
            throw new MachineNotLoadedException();
        }
        if(getIsMachineUsable()){
            enigmaMachine.resetCurrentSettings();
        }
        randomizer.randomizeMachine(enigmaMachine);
        originalCodeFormation = MachineCodeForm.updateCodeForm(enigmaMachine);
        enigmaMachine.setIsMachineUsable(true);
        return originalCodeFormation;
    }

    @Override
    public void setMachineForUse(List<String> choices, SetForUseChoices choiceType) {
        if (!isMachineLoaded) {
            throw new MachineNotLoadedException();
        }
            validationManager.validateSetMachine(choices, choiceType, enigmaMachine);
            setEnigmaMachinePart(choices, choiceType);
    }

    private void setEnigmaMachinePart(List<String> choices, SetForUseChoices choiceType) {
        for (String choice : choices) {
            switch (choiceType) {
                case ROTOR:
                    enigmaMachine.setUsedRotor(choice);
                    break;
                case REFLECTOR:
                    enigmaMachine.setUsedReflector(choice);
                    break;
                case ROTORSLIDEPOS:
                    enigmaMachine.setRotorToTop(choiceType.getRotorInd(), choice);
                    choiceType.setNextRotorInd();
                    break;
                case PLUG:
                    enigmaMachine.setPlug(choice.substring(0, 1), choice.substring(1));
                    break;
                case ALLSET:
                    enigmaMachine.setIsMachineUsable(true);
                    originalCodeFormation = MachineCodeForm.updateCodeForm(enigmaMachine);
                    break;
            }
        }
    }

    @Override
    public MachineDetailsDTO getMachineDetails() {
        if(!isMachineLoaded){
            return null;
        }
        boolean isMachineInUse = enigmaMachine.isMachineReadyForUse();
        int numOfEncryptionsMade = engineStats.getNumEncrypted();
        ReflectorDTO reflectorDTO = new ReflectorDTO(enigmaMachine.getTotalNumOfReflectors());
        RotorDTO rotorDetails = new RotorDTO(enigmaMachine.getTotalNumOfRotors(), enigmaMachine.getNumOfRotorsInUse());
        KeyboardDTO keyboardDetails = new KeyboardDTO(enigmaMachine.getUsedKeyboard().getKeyboardABC(), enigmaMachine.getUsedKeyboard().getKeyboardSize());
        MachineGeneralDetailsDTO numberDetails = new MachineGeneralDetailsDTO(rotorDetails, reflectorDTO, keyboardDetails, numOfEncryptionsMade);
        MachineDetailsDTO machineDetailsDTO = new MachineDetailsDTO(numberDetails, isMachineInUse);
        if (isMachineInUse) {
            MachineCodeForm currentForm = MachineCodeForm.updateCodeForm(enigmaMachine);
            MachineInUseDTO inUseDetails = new MachineInUseDTO(originalCodeFormation, currentForm);
            machineDetailsDTO.setInUsedDetails(inUseDetails);
        }
        return machineDetailsDTO;
    }

    @Override
    public void createMachineFromXML(Path xmlFilePath){
        if(!Files.exists(xmlFilePath)){
            throw new FileNotExistException(xmlFilePath.toString());
        }
        if(!xmlFilePath.toString().endsWith(".xml")){
            throw new FileDoesNotEndAsXML(xmlFilePath.toString());
        }
        CTEEnigma enigmaXML = createXMLEnigmaInstance(xmlFilePath.toString());
        validationManager.setMachineLoadXML(enigmaXML.getCTEMachine());
        validationManager.validateLoadXML();
        enigmaMachine = enigmaCreator.createEnigmaPartsAndMachine(enigmaXML);
        if(isMachineLoaded) {
            engineStats.reset();
        }
            isMachineLoaded = true;
    }

    private CTEEnigma createXMLEnigmaInstance(String fullPath) {
        CTEEnigma enigma = new CTEEnigma();
        try {
            InputStream inputStream = new FileInputStream(fullPath);
            enigma = deserializeFrom(inputStream);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();

        }
        return enigma;
    }

    private CTEEnigma deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_ENIGMA_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (CTEEnigma) u.unmarshal(in);
    }

}
