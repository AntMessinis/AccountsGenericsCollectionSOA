package controller;

import dto.*;
import service.*;
import service.exceptions.AccountDoesNotExistException;

public class UpdateController {
    private final HelperController helper = new HelperController();
    private final AccountServiceImpl accountService = AccountServiceImpl.getService();
    private final JointAccountServiceImpl jointAccountService = JointAccountServiceImpl.getService();
    private final OverdraftAccountServiceImpl overdraftAccountService = OverdraftAccountServiceImpl.getService();
    private final JointOverdraftAccountServiceImpl jointOverdraftAccountService = JointOverdraftAccountServiceImpl.getService();

    public void updateAccountHandler() throws AccountDoesNotExistException {
        helper.accountTypeMenu();
        int typeChoice = -1;
        do {
            try{
                typeChoice = helper.getChoice();
            } catch (NumberFormatException e){
                System.out.println("Invalid input! Please try again.");
            }
        } while (typeChoice < 1 || typeChoice > 4);

        long id = helper.getID();
        handleUpdate(typeChoice, id);
    }

    private void handleUpdate(int typeChoice, long id) throws AccountDoesNotExistException {
        System.out.println("Update Account's holder's details");
        UserDTO updatedHolder = helper.getHolderInfo();
        UserDTO updateSecondHolder = null;

        if (typeChoice == 3 || typeChoice == 4){
            System.out.println("Update Account's second holder's details");
            updateSecondHolder = helper.getHolderInfo();
        }
        switch (typeChoice){
            case 1:
                AccountDTO simpleDto = new AccountDTO();

                simpleDto.setHolder(updatedHolder);

                accountService.updateAccount(id, simpleDto);
                break;
            case 2:
                OverdraftAccountDTO overdraftDto = new OverdraftAccountDTO();

                overdraftDto.setHolder(updatedHolder);

                overdraftAccountService.updateAccount(id, overdraftDto);
                break;
            case 3:
                JointAccountDTO jointDto = new JointAccountDTO();

                jointDto.setHolder(updatedHolder);
                jointDto.setSecondHolder(updateSecondHolder);

                jointAccountService.updateAccount(id, jointDto);
                break;
            case 4:
                JointOverdraftAccountDTO jointOverdraftDto = new JointOverdraftAccountDTO();

                jointOverdraftDto.setHolder(updatedHolder);
                jointOverdraftDto.setSecondHolder(updateSecondHolder);

                jointOverdraftAccountService.updateAccount(id, jointOverdraftDto);
                break;
        }
    }
}
