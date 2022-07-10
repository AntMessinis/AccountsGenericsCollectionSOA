package controller;

import service.*;
import service.exceptions.AccountDoesNotExistException;

public class AccountDetailsController {
    private final HelperController helper = new HelperController();
    private final AccountServiceImpl accountService = AccountServiceImpl.getService();
    private final JointAccountServiceImpl jointAccountService = JointAccountServiceImpl.getService();
    private final OverdraftAccountServiceImpl overdraftAccountService = OverdraftAccountServiceImpl.getService();
    private final JointOverdraftAccountServiceImpl jointOverdraftAccountService = JointOverdraftAccountServiceImpl.getService();

    public void getAccountDetailsHandler() throws AccountDoesNotExistException {
        helper.accountTypeMenu();
        int type = -1;
        do {
            try {
                type  = helper.getChoice();
            } catch (NumberFormatException e){
                System.out.println("Invalid input! Please try again.");
            }
        } while (type < 1 || type > 4);

        long id = helper.getID();
        handleChoice(type, id);
    }

    private void handleChoice(int type, long id) throws AccountDoesNotExistException {
        switch (type){
            case 1:
                System.out.println(accountService.getAccount(id).toString());
                break;
            case 2:
                System.out.println(overdraftAccountService.getAccount(id).toString());
                break;
            case 3:
                System.out.println(jointAccountService.getAccount(id).toString());
                break;
            case 4:
                System.out.println(jointOverdraftAccountService.getAccount(id).toString());
                break;
        }
    }
}
