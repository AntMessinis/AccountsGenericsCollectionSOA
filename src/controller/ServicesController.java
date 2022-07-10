package controller;

import service.*;
import service.exceptions.AccountDoesNotExistException;
import service.exceptions.InsufficientBalanceException;
import service.exceptions.InvalidSSNException;

public class ServicesController{
    private final HelperController helper = new HelperController();
    private final AccountServiceImpl accountService = AccountServiceImpl.getService();
    private final JointAccountServiceImpl jointAccountService = JointAccountServiceImpl.getService();
    private final OverdraftAccountServiceImpl overdraftAccountService = OverdraftAccountServiceImpl.getService();
    private final JointOverdraftAccountServiceImpl jointOverdraftAccountService = JointOverdraftAccountServiceImpl.getService();


    void useAccountServicesHandler() throws InvalidSSNException, InsufficientBalanceException, AccountDoesNotExistException {
        showAccountServicesMenu();
        int serviceChoice = 0;
        do {
            try{
                serviceChoice = helper.getChoice();
            } catch (NumberFormatException e){
                System.out.println("Invalid input please try again.");
            }
        } while (serviceChoice < 1 || serviceChoice > 2);
        
        handleServiceChoice(serviceChoice);
    }



    private void showAccountServicesMenu(){
        System.out.println("Please choose between the following services");
        System.out.println("\t1) Deposit to account");
        System.out.println("\t2) Withdraw from your account");
        System.out.print("Please enter a number that corresponds to an option: ");
    }

    private void handleServiceChoice(int serviceChoice) throws InvalidSSNException, InsufficientBalanceException, AccountDoesNotExistException {
        helper.accountTypeMenu();
        int typechoice = -1;

        do {
            try{
                typechoice = helper.getChoice();
            } catch (NumberFormatException e){
                System.out.println();
            } System.out.println("Invalid input! Please try again.");
        } while (typechoice < 1 || typechoice > 4);


        long id = helper.getID();
        String ssn = helper.getSsn();

        double amount = -1;
        do {
            try{
                System.out.print("How much money do you want to deposit: ");
                amount = helper.getAmount();
            }catch (NumberFormatException e){
                System.out.println("Invalid input! Please try again.");
            }
        } while (amount < 0);

        switch (serviceChoice){
            case 1:
                depositHandler(typechoice, id, ssn, amount);
                break;
            case 2:
                withdrawHandler(typechoice, id, ssn, amount);
                break;
        }

    }

    private void depositHandler(int typechoice,long id , String ssn, double amount) throws InvalidSSNException, AccountDoesNotExistException {
        switch (typechoice){
            case 1:
                accountService.depositToAccount(id,ssn,amount);
                break;
            case 2:
                overdraftAccountService.depositToAccount(id,ssn,amount);
                break;
            case 3:
                jointAccountService.depositToAccount(id,ssn,amount);
                break;
            case 4:
                jointOverdraftAccountService.depositToAccount(id,ssn,amount);
                break;
        }
    }

    private void withdrawHandler(int typechoice,long id , String ssn, double amount) throws InvalidSSNException, InsufficientBalanceException, AccountDoesNotExistException {
        switch (typechoice){
            case 1:
                accountService.withdrawFromAccount(id,ssn,amount);
                break;
            case 2:
                overdraftAccountService.withdrawFromAccount(id,ssn,amount);
                break;
            case 3:
                jointAccountService.withdrawFromAccount(id,ssn,amount);
                break;
            case 4:
                jointOverdraftAccountService.withdrawFromAccount(id,ssn,amount);
                break;
        }
    }
}
