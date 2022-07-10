package service;

import dto.AbstractDTO;
import model.IAccount;
import service.exceptions.AccountAlreadyExistsException;
import service.exceptions.AccountDoesNotExistException;
import service.exceptions.InsufficientBalanceException;
import service.exceptions.InvalidSSNException;

import java.util.Map;

public interface IAccountService<D extends AbstractDTO,A extends IAccount> {
    void createAccount(D dto) throws AccountAlreadyExistsException;
    A getAccount(long id) throws AccountDoesNotExistException;
    void  updateAccount(long id, D dto) throws AccountDoesNotExistException;
    void deleteAccount(long id) throws AccountDoesNotExistException;
    Map<Long, A> getAllAccounts();
    void depositToAccount(long id, String ssn, double amount) throws InvalidSSNException, AccountDoesNotExistException;
    void withdrawFromAccount(long id, String ssn, double amount) throws InsufficientBalanceException, InvalidSSNException, AccountDoesNotExistException;

}
