package service;

import dao.IJointOverdraftAccountDAO;
import dao.JointOverdraftAccountDAOImpl;
import dto.JointOverdraftAccountDTO;
import model.JointOverdraftAccount;
import model.User;
import service.exceptions.AccountAlreadyExistsException;
import service.exceptions.AccountDoesNotExistException;
import service.exceptions.InsufficientBalanceException;
import service.exceptions.InvalidSSNException;

import java.util.Map;

public class JointOverdraftAccountServiceImpl implements IAccountService<JointOverdraftAccountDTO, JointOverdraftAccount>{
    private final ServiceHelper<JointOverdraftAccountDTO, JointOverdraftAccount> helper = new ServiceHelper<>();
    private final IJointOverdraftAccountDAO DAO;

    //This class has a single instance
    private static final JointOverdraftAccountServiceImpl jointAccountService = new JointOverdraftAccountServiceImpl();

    private JointOverdraftAccountServiceImpl() {
        DAO = new JointOverdraftAccountDAOImpl();
    }

    //Return the single instance
    public static JointOverdraftAccountServiceImpl getService(){
        return jointAccountService;
    }

    @Override
    public void createAccount(JointOverdraftAccountDTO dto) throws AccountAlreadyExistsException {
        User holder = helper.extractHolder(dto);
        User secondHolder = helper.extractSecondHolder(dto);

        String iban = helper.extractIban(dto);
        double balance= helper.extractBalanceAmount(dto);

        JointOverdraftAccount newAccount = new JointOverdraftAccount(holder,balance,iban, secondHolder);
        long id = newAccount.getId();

        if (!DAO.accountExists(id)){

            DAO.insert(id, newAccount);
            System.out.println("Account created successfully");
            System.out.println("Your new account's info is " + newAccount);
        } else throw new AccountAlreadyExistsException(id);
    }

    @Override
    public JointOverdraftAccount getAccount(long id) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            return DAO.get(id);
        } else {
            throw new AccountDoesNotExistException(id);
        }
    }

    @Override
    public void updateAccount(long id, JointOverdraftAccountDTO dto) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            User holder = helper.extractHolder(dto);
            User secondHolder = helper.extractSecondHolder(dto);

            double balance= helper.extractBalanceAmount(dto);

            JointOverdraftAccount updatedAccount = DAO.get(id);
            updatedAccount.setHolder(holder);
            updatedAccount.setSecondHolder(secondHolder);

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
    public Map<Long, JointOverdraftAccount> getAllAccounts() {
        return DAO.getAll();
    }

    @Override
    public void depositToAccount(long id, String ssn, double amount) throws InvalidSSNException, AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            JointOverdraftAccount account = DAO.get(id);

            if (helper.validateSsn(ssn, account.getHolder()) || helper.validateSsn(ssn, account.getSecondHolder())){

                double depositedAmount = account.getBalance();
                account.setBalance(depositedAmount + amount);
                System.out.println("You have deposited " + amount +" amount of money successfully");
            } else throw new InvalidSSNException(ssn);

        } else throw new AccountDoesNotExistException(id);
    }

    @Override
    public void withdrawFromAccount(long id, String ssn, double amount) throws InsufficientBalanceException, InvalidSSNException, AccountDoesNotExistException {
        if (DAO.accountExists(id)) {
            JointOverdraftAccount account = DAO.get(id);

            if (helper.validateSsn(ssn, account.getHolder()) || helper.validateSsn(ssn, account.getSecondHolder())){
                double depositedAmount = DAO.get(id).getBalance();

                if ((depositedAmount - amount) > 0 ){
                    DAO.get(id).setBalance(depositedAmount - amount);
                    System.out.println("You have withdrawed " + amount +" amount of money successfully");
                } else throw new InsufficientBalanceException(depositedAmount, amount);

            } else throw new InvalidSSNException(ssn);

        } throw new AccountDoesNotExistException(id);

    }
}
