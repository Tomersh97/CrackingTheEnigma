package dtos;

public class ExceptionDTO {
    String causedBy;
    String reason;
    String solution;

    private final static String EXCEPTION_NOTIFY = "A problem was found.";
    private final static String EXCEPTION_CAUSED_MESSAGE = "The problem was caused by: ";
    private final static String EXCEPTION_REASON_MESSAGE = "The reason for the problem is: ";
    private final static String EXCEPTION_SOLUTION_MESSAGE = "A possible solution would be: ";

    public ExceptionDTO(String _causedBy, String _reason, String _solution){
        causedBy = _causedBy;
        reason =   _reason;
        solution = _solution;
    }

    @Override
    public String toString() {
        return
                "\n" + EXCEPTION_NOTIFY + "\n" +
                EXCEPTION_CAUSED_MESSAGE + causedBy + "\n"
                + EXCEPTION_REASON_MESSAGE + reason + "\n"
                + EXCEPTION_SOLUTION_MESSAGE + solution + "\n";
    }
}
