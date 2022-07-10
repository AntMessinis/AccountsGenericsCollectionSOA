package service;

import dao.AccountDAOImpl;
import dao.IAccountDAO;
import dto.AccountDTO;
import model.Account;
import model.User;
import service.exceptions.AccountAlreadyExistsException;
import service.exceptions.AccountDoesNotExistException;
import service.exceptions.InsufficientBalanceException;
import service.exceptions.InvalidSSNException;

import java.util.Map;

public class AccountServiceImpl implements IAccountService<AccountDTO, Account>{

    private final IAccountDAO DAO = new AccountDAOImpl();
    private final ServiceHelper<AccountDTO> helper = new ServiceHelper<>();

    //This class has a single instance
    private static final AccountServiceImpl accountService = new AccountServiceImpl();

    private AccountServiceImpl() {
    }

    //Return the single instance
    public static AccountServiceImpl getService(){
        return accountService;
    }


    @Override
    public void createAccount(AccountDTO dto) throws AccountAlreadyExistsException {
        User holder = helper.extractHolder(dto);
        String iban = helper.extractIban(dto);
        double balance= helper.extractBalanceAmount(dto);
        Account newAccount = new Account(holder,balance,iban);
        long id = newAccount.getId();

        if (!DAO.accountExists(id)){

            DAO.insert(id, newAccount);

            System.out.println("Account created successfully");
            System.out.println("Your new account's info is " + newAccount);

        } else throw new AccountAlreadyExistsException(id);
    }

    @Override
    public Account getAccount(long id) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            return DAO.get(id);
        } else {
            throw new AccountDoesNotExistException(id);
        }
    }

    @Override
    public void updateAccount(long id, AccountDTO dto) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            User holder = helper.extractHolder(dto);
            Account updatedAccount = DAO.get(id);

            updatedAccount.setHolder(holder);

            DAO.update(id, updatedAccount);
            System.out.println("Account updated successfully");
            System.out.println("Your updated account's info is " + updatedAccount);
            
        } else throw new AccountDoesNotExistException(id);

    }

    @Override
    public void deleteAccount(long id) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            DAO.delete(id);
            System.out.println("Account deleted successfully");
        } else throw new AccountDoesNotExistException(id);
    }

    @Override
    public Map<Long, Account> getAllAccounts() {
        return DAO.getAll();
    }

    @Override
    public void depositToAccount(long id, String ssn, double amount) throws InvalidSSNException, AccountDoesNotExistException {
        if (DAO.accountExists(id)){

            Account account = DAO.get(id);

            if (helper.validateSsn(ssn, account.getHolder())){
                double depositedAmount = account.getBalance();
                account.setBalance(depositedAmount + amount);
                System.out.println("You have deposited " + amount +" amount of money successfully");
            } else {
                throw new InvalidSSNException(ssn);
            }

        } else throw new AccountDoesNotExistException(id);

    }

    @Override
    public void withdrawFromAccount(long id, String ssn, double amount) throws InsufficientBalanceException, InvalidSSNException, AccountDoesNotExistException {
        if (DAO.accountExists(id)){

            Account account = DAO.get(id);

            if (helper.validateSsn(ssn, account.getHolder())){
                double depositedAmount = DAO.get(id).getBalance();

                if ((depositedAmount - amount) > 0 ){
                    account.setBalance(depositedAmount - amount);
                    System.out.println("You have withdrawed " + amount +" amount of money successfully");
                } else throw new InsufficientBalanceException(depositedAmount, amount);

            } else throw new InvalidSSNException(ssn);

        } else throw new AccountDoesNotExistException(id);
    }
}
