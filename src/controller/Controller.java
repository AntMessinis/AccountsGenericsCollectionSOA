package controller;

import service.exceptions.AccountAlreadyExistsException;
import service.exceptions.AccountDoesNotExistException;
import service.exceptions.InsufficientBalanceException;
import service.exceptions.InvalidSSNException;

public class Controller {
    private final CreateController createController = new CreateController();
    private final ServicesController serviceController = new ServicesController();
    private final AccountDetailsController accountDetailsController = new AccountDetailsController();
    private final UpdateController updateController = new UpdateController();
    private final DeleteController deleteController = new DeleteController();


    public void createHandler() throws AccountDoesNotExistException, AccountAlreadyExistsException {
        createController.createAccountHandler();
    }

    public void useAccountServicesHandler() throws InvalidSSNException, InsufficientBalanceException, AccountDoesNotExistException {
        serviceController.useAccountServicesHandler();
    }

    public void getAccountDetailsHandler() throws AccountDoesNotExistException {
        accountDetailsController.getAccountDetailsHandler();
    }

    public void updateAccountHandler() throws AccountDoesNotExistException {
        updateController.updateAccountHandler();
    }

    public void deleteHandler() throws AccountDoesNotExistException {
        deleteController.deleteHandler();
    }
}
