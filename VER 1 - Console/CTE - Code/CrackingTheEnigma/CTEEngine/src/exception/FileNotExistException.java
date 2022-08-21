package exception;

import dtos.ExceptionDTO;

public class FileNotExistException extends EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;
    public FileNotExistException(String filePath){
        exceptionDetails = new ExceptionDTO(
                "given path: " + filePath,
                "file does not exist",
                "make sure the filepath is correct, full and ends in .xml");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
