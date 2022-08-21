package exception;

import dtos.ExceptionDTO;

public class FileDoesNotEndAsXML extends  EnigmaEngineException{
    private final ExceptionDTO exceptionDetails;

    public FileDoesNotEndAsXML(String pathname){
        exceptionDetails = new ExceptionDTO(
                "path given: " + pathname,
                "does not end with .xml",
                "make sure the filepath given ends with .xml");
    }


    @Override
    public ExceptionDTO getExceptionDetails() {
        return exceptionDetails;
    }
}
