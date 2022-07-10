import controller.Controller;
import service.exceptions.AccountAlreadyExistsException;
import service.exceptions.AccountDoesNotExistException;
import service.exceptions.InsufficientBalanceException;
import service.exceptions.InvalidSSNException;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final Controller controller = new Controller();

    public static void main(String[] args) throws InvalidSSNException, InsufficientBalanceException, AccountDoesNotExistException, AccountAlreadyExistsException {
        int choice = -1;
        do {
            showMenu();

            do {
                try {
                    choice = getChoice();
                } catch (NumberFormatException e){
                    System.out.println("Invalid input please try again.");
                }
            } while (choice < 1 || choice > 6);


            handleChoice(choice);
        } while (choice != 6);

        in.close();
    }

    private static void showMenu(){
        System.out.println("Welcome to Antonis' Bank app");
        System.out.println("\tPlease select from the following options");
        System.out.println("\t1) Create a new Account");
        System.out.println("\t2) Show Account Details");
        System.out.println("\t3) Update Account Details");
        System.out.println("\t4) Use Account Services");
        System.out.println("\t5) Delete Account");
        System.out.println("\t6) Exit Antonis' Bank app");
        System.out.println();
    }

    private static int getChoice(){
        int choice = -1;
        do {
            try {
                System.out.print("Please Enter Choice: ");
                choice = in.nextInt();
            } catch (NumberFormatException e){
                System.out.println("Invalid input please try again. ");
            }
        } while ((choice < 1) || (choice > 6));

        return choice;
    }

    private static void handleChoice(int choice) throws InvalidSSNException, InsufficientBalanceException, AccountDoesNotExistException, AccountAlreadyExistsException {
        switch (choice){
            case 1: controller.createHandler();
                break;
            case 2: controller.getAccountDetailsHandler();
                break;
            case 3: controller.updateAccountHandler();
                break;
            case 4: controller.useAccountServicesHandler();
                break;
            case 5: controller.deleteHandler();
                break;
            case 6:
                System.out.println("Exiting Application...");
                break;
            default:
                System.out.println("Your input does not correspond to a choice");
                System.out.println("Please try again");
                break;
        }
    }
}
