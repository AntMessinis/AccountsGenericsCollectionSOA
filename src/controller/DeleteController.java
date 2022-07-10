package controller;

import service.*;
import service.exceptions.AccountDoesNotExistException;

import java.util.Scanner;

public class DeleteController {
    private final Scanner in = new Scanner(System.in);
    private final HelperController helper = new HelperController();
    private final AccountServiceImpl accountService = AccountServiceImpl.getService();
    private final JointAccountServiceImpl jointAccountService = JointAccountServiceImpl.getService();
    private final OverdraftAccountServiceImpl overdraftAccountService = OverdraftAccountServiceImpl.getService();
    private final JointOverdraftAccountServiceImpl jointOverdraftAccountService = JointOverdraftAccountServiceImpl.getService();

    public void deleteHandler() throws AccountDoesNotExistException {
        helper.accountTypeMenu();

        int typeChoice =-1;

        do {
            try {
                typeChoice = helper.getChoice();
            } catch (NumberFormatException e){
                System.out.println("Invalid input please try again.");
            }
        } while (typeChoice < 1 || typeChoice > 4);

        long id = helper.getID();
        String ssn = helper.getSsn();

        handleChoice(typeChoice, id, ssn);
    }

    private void handleChoice(int typeChoice, long id, String ssn) throws AccountDoesNotExistException {
        printConfirmationMessage(id);
        boolean confirmation = deleteConfirmation();
        if (confirmation){
            switch (typeChoice){
                case 1:
                    if (accountService.getAccount(id).getHolder().getSsn().equals(ssn)) accountService.deleteAccount(id);
                    break;
                case 2:
                    if (overdraftAccountService.getAccount(id).getHolder().getSsn().equals(ssn)) overdraftAccountService.deleteAccount(id);
                    break;
                case 3:
                    if ((jointAccountService.getAccount(id).getHolder().getSsn().equals(ssn))||(jointAccountService.getAccount(id).getSecondHolder().getSsn().equals(ssn)))
                        jointAccountService.deleteAccount(id);
                    break;
                case 4:
                    if ((jointOverdraftAccountService.getAccount(id).getHolder().getSsn().equals(ssn)) || (jointOverdraftAccountService.getAccount(id).getSecondHolder().getSsn().equals(ssn)))
                        jointOverdraftAccountService.deleteAccount(id);
                    break;
            }
        }
    }

    private void printConfirmationMessage(long id){
        System.out.println("Are you sure you want to delete account with id: " + id + "?");

    }

    private boolean deleteConfirmation(){
        String answer;

        do {
            System.out.print("Answer (y/n):");
            answer = in.next().trim().toLowerCase();
        } while ((answer != "y") || (answer != "n"));

        return answer == "y";
    }
}
