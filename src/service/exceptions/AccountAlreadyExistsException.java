package service.exceptions;

public class AccountAlreadyExistsException extends Exception{
    private static final long serialVersionUID = 1L;

    public AccountAlreadyExistsException(long id) {
        super("Account with iban '" + id + "' already exist");
    }
}
