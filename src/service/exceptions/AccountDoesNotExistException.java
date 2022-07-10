package service.exceptions;

public class AccountDoesNotExistException extends Exception{
    private static final long serialVersionUID = 1L;

    public AccountDoesNotExistException(long id) {
        super("Banck account with id: '" + id + "' does not exist in Account database.");
    }
}
