package controller;

import dto.*;
import service.*;
import service.exceptions.AccountAlreadyExistsException;
import service.exceptions.AccountDoesNotExistException;

import java.util.Random;

public class CreateController {
    private final HelperController helper = new HelperController();
    private final AccountServiceImpl accountService = AccountServiceImpl.getService();
    private final JointAccountServiceImpl jointAccountService = JointAccountServiceImpl.getService();
    private final OverdraftAccountServiceImpl overdraftAccountService = OverdraftAccountServiceImpl.getService();
    private final JointOverdraftAccountServiceImpl jointOverdraftAccountService = JointOverdraftAccountServiceImpl.getService();

    void createAccountHandler() throws AccountDoesNotExistException, AccountAlreadyExistsException {


        showCreationMenu();
        int typeChoice = -1;
        do {
            try {
                typeChoice = helper.getChoice();
            } catch (NumberFormatException e){
                System.out.println("Invalid Input please try again.");
            }
        } while (typeChoice < 1 || typeChoice > 4);
                
        switch (typeChoice){
            case 1:
                CreateSimpleAccount();
                break;
            case 2:
                CreateOverdraftAccount();
                break;
            case 3:
                CreateJointAccount();
                break;
            case 4:
                CreateJointOverdraftAccount();
                break;
            default:
                System.out.println("Your input does not correspond to one of the available choices.");
        }
    }

    private void CreateJointOverdraftAccount() throws AccountDoesNotExistException, AccountAlreadyExistsException {
        UserDTO holder = helper.getHolderInfo();
        UserDTO secondHolder = helper.getHolderInfo();
        String iban = createIban();
        double balance = getBalance();
        JointOverdraftAccountDTO dto = new JointOverdraftAccountDTO(holder,iban,balance, secondHolder);
        jointOverdraftAccountService.createAccount(dto);
    }

    private void CreateJointAccount() throws AccountAlreadyExistsException {
        UserDTO holder = helper.getHolderInfo();
        UserDTO secondHolder = helper.getHolderInfo();
        String iban = createIban();
        double balance = getBalance();
        JointAccountDTO dto = new JointAccountDTO(holder,iban,balance, secondHolder);
        jointAccountService.createAccount(dto);
    }

    private void CreateOverdraftAccount() throws AccountAlreadyExistsException {
        UserDTO holder = helper.getHolderInfo();
        String iban = createIban();
        double balance = getBalance();
        OverdraftAccountDTO dto = new OverdraftAccountDTO(holder,iban,balance);
        overdraftAccountService.createAccount(dto);
    }

    private void CreateSimpleAccount() throws AccountAlreadyExistsException {
        UserDTO holder = helper.getHolderInfo();
        String iban = createIban();
        double balance = getBalance();
        AccountDTO dto = new AccountDTO(holder,iban,balance);
        accountService.createAccount(dto);
    }

    private void showCreationMenu(){
        System.out.println("Please enter the type of account you want to create");
        System.out.println("\t1)Simple Deposit Account");
        System.out.println("\t2)Overdraft Account");
        System.out.println("\t3)Joint Account");
        System.out.println("\t4)Joint Overdraft Account");
        System.out.print("Please enter a number that corresponds to an option: ");
    }
    
    

    private double getBalance(){
        System.out.print("Please enter the account's starting balance: ");
        double balance = helper.getAmount();
        return balance;
    }


    private String createIban(){
        String ibanPrefix = "GR10";
        String newIban = ibanPrefix;
        Random r = new Random();

        for (int i = 0; i < 5; i++){
            newIban += r.nextInt(10);
        }

        return newIban;
    }
}
