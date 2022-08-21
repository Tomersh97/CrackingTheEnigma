package ui.exception;

import dtos.ExceptionDTO;


public class FileDirectoryDoesNotExist extends  EnigmaUIException{
    private final ExceptionDTO exceptionDetails;

    public FileDirectoryDoesNotExist(String filePath) {
        exceptionDetails = new ExceptionDTO(
                "file-path given: " + filePath + ".",
                "directory does not exist.",
                "please choose a valid directory.");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
