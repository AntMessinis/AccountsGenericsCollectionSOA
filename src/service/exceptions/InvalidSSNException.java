package service.exceptions;

public class InvalidSSNException extends Exception{
    private static final long serialVersionUID = 1L;

    public InvalidSSNException(String ssn) {
        super("The provided ssn: '" + ssn + "' is not valid." );
    }
}
