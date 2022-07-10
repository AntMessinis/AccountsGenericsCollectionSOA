package service;

import dao.IOverdraftAccountDAO;
import dao.OverdraftAccountDAOImpl;
import dto.OverdraftAccountDTO;
import model.OverdraftAccount;
import model.User;
import service.exceptions.AccountAlreadyExistsException;
import service.exceptions.AccountDoesNotExistException;
import service.exceptions.InvalidSSNException;

import java.util.Map;

public class OverdraftAccountServiceImpl implements IAccountService<OverdraftAccountDTO, OverdraftAccount>{
    private final ServiceHelper<OverdraftAccountDTO> helper = new ServiceHelper<>();
    private final IOverdraftAccountDAO DAO;

    //This class has a single instance
    private static final OverdraftAccountServiceImpl overdraftAccountService = new OverdraftAccountServiceImpl();

    private OverdraftAccountServiceImpl() {
        DAO = new OverdraftAccountDAOImpl();
    }


    //Return the single instance
    public static OverdraftAccountServiceImpl getService(){
        return overdraftAccountService;
    }

    @Override
    public void createAccount(OverdraftAccountDTO dto) throws AccountAlreadyExistsException {
        User holder = helper.extractHolder(dto);
        String iban = helper.extractIban(dto);
        double balance= helper.extractBalanceAmount(dto);

        OverdraftAccount newAccount = new OverdraftAccount(holder,balance,iban);
        long id = newAccount.getId();

        if (!DAO.accountExists(id)){

            DAO.insert(id, newAccount);
            System.out.println("Account created successfully");
            System.out.println("Your new account's info is " + newAccount);
        } else throw new AccountAlreadyExistsException(id);

    }

    @Override
    public OverdraftAccount getAccount(long id ) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            return DAO.get(id);
        } else {
            throw new AccountDoesNotExistException(id);
        }
    }

    @Override
    public void updateAccount(long id , OverdraftAccountDTO dto) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            User holder = helper.extractHolder(dto);

            OverdraftAccount updatedAccount = DAO.get(id);
            updatedAccount.setHolder(holder);
            DAO.update(id, updatedAccount);
            System.out.println("Account updated successfully");
            System.out.println("Your updated account's info is " + updatedAccount);
        } else throw new AccountDoesNotExistException(id);

    }

    @Override
    public void deleteAccount(long id ) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            DAO.delete(id);
            System.out.println("Account deleted successfully");
        } else throw new AccountDoesNotExistException(id);

    }

    @Override
    public Map<Long, OverdraftAccount> getAllAccounts() {
       return DAO.getAll();
    }

    @Override
    public void depositToAccount(long id , String ssn, double amount) throws InvalidSSNException, AccountDoesNotExistException {
        if (DAO.accountExists(id)){

            OverdraftAccount account = DAO.get(id);

            if (helper.validateSsn(ssn, account.getHolder())) {
                double depositedAmount = account.getBalance();
                account.setBalance(depositedAmount + amount);
                System.out.println("You have deposited " + amount +" amount of money successfully");
            } else throw new InvalidSSNException(ssn);

        } else throw new AccountDoesNotExistException(id);

    }

    @Override
    public void withdrawFromAccount(long id , String ssn, double amount) throws InvalidSSNException, AccountDoesNotExistException {
        if (DAO.accountExists(id)){

            OverdraftAccount account = DAO.get(id);

            if (helper.validateSsn(ssn, account.getHolder())){
                double depositedAmount = DAO.get(id).getBalance();
                account.setBalance(depositedAmount - amount);
                System.out.println("You have withdrawed " + amount +" amount of money successfully");
            } else throw new InvalidSSNException(ssn);

        } else throw new AccountDoesNotExistException(id);
    }
}
