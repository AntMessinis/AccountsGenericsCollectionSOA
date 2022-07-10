package service;

import dao.IJointAccoundDAO;
import dao.JointAccountDAOImpl;
import dto.JointAccountDTO;
import model.JointAccount;
import model.User;
import service.exceptions.AccountAlreadyExistsException;
import service.exceptions.AccountDoesNotExistException;
import service.exceptions.InsufficientBalanceException;
import service.exceptions.InvalidSSNException;

import java.util.Map;

public class JointAccountServiceImpl implements IAccountService<JointAccountDTO, JointAccount>{
    private final ServiceHelper<JointAccountDTO> helper = new ServiceHelper<>();
    private final IJointAccoundDAO DAO;

    //This class has a single instance
    private static final JointAccountServiceImpl jointAccountService = new JointAccountServiceImpl();

    //Return the single instance
    public static JointAccountServiceImpl getService(){
        return jointAccountService;
    }

    private JointAccountServiceImpl() {
        DAO = new JointAccountDAOImpl();
    }

    @Override
    public void createAccount(JointAccountDTO dto) throws AccountAlreadyExistsException {
        User holder = helper.extractHolder(dto);
        User secondHolder = helper.extractSecondHolder(dto);

        String iban = helper.extractIban(dto);
        double balance= helper.extractBalanceAmount(dto);

        JointAccount newAccount = new JointAccount(holder,balance,iban, secondHolder);
        long id = newAccount.getId();


        if (!DAO.accountExists(id)) {

            DAO.insert(id, newAccount);
            System.out.println("Account created successfully");
            System.out.println("Your new account's info is " + newAccount);
        } else throw new AccountAlreadyExistsException(id);
    }

    @Override
    public JointAccount getAccount(long id) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)){
            return DAO.get(id);
        } else {
            throw new AccountDoesNotExistException(id);
        }
    }

    @Override
    public void updateAccount(long id, JointAccountDTO dto) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)) {
            User holder = helper.extractHolder(dto);
            User secondHolder = helper.extractSecondHolder(dto);

            JointAccount updatedAccount = DAO.get(id);

            updatedAccount.setHolder(holder);
            updatedAccount.setSecondHolder(secondHolder);

            DAO.update(id, updatedAccount);
            System.out.println("Account updated successfully");
            System.out.println("Your updated account's info is " + updatedAccount);
        } else throw new AccountDoesNotExistException(id);

    }

    @Override
    public void deleteAccount(long id) throws AccountDoesNotExistException {
        if (DAO.accountExists(id)) {
            DAO.delete(id);
            System.out.println("Account deleted successfully");
        } else throw new AccountDoesNotExistException(id);
    }

    @Override
    public Map<Long, JointAccount> getAllAccounts() {
        return DAO.getAll();
    }

    @Override
    public void depositToAccount(long id, String ssn, double amount) throws InvalidSSNException, AccountDoesNotExistException {
        if (DAO.accountExists(id)) {
            JointAccount account = DAO.get(id);

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
            JointAccount account = DAO.get(id);

            if (helper.validateSsn(ssn, account.getHolder()) || helper.validateSsn(ssn, account.getSecondHolder())){

                double depositedAmount = DAO.get(id).getBalance();

                if ((depositedAmount - amount) > 0 ){
                    account.setBalance(depositedAmount - amount);
                    System.out.println("You have withdrawed " + amount +" amount of money successfully");
                } else throw new InsufficientBalanceException(depositedAmount, amount);

            } else throw new InvalidSSNException(ssn);

        } else throw new AccountDoesNotExistException(id);

    }
}
