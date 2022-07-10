package controller;

import dto.UserDTO;

import java.util.Scanner;

public class HelperController {
    private static Scanner in = new Scanner(System.in);
    String getSsn(){
        String ssn;
        do {
            System.out.print("Please enter your ssn (9 digits): ");
            ssn = in.next();
        } while (!isSsn(ssn));
        return ssn;
    }

    String getIban(){
        String iban;
        do {
            System.out.println("Please type your account's iban");
            System.out.print("Iban: ");
            iban = in.next().trim();
            if (!isIban(iban)){
                System.out.println("Your input is not an IBAN please try again.");
            }
        } while (!isIban(iban));

        return iban;
    }

    boolean isIban(String iban){
        if (iban.startsWith("GR") && (iban.length() == 9)){
            return true;
        }
        return false;
    }


    int getChoice(){
        int choice = in.nextInt();
        return choice;
    }

    boolean isInt(String ssn){
        try {
            Integer.parseInt(ssn);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isSsn(String ssn){
        if (isInt(ssn)) return (ssn.length() == 9);
        return false;
    }

    double getAmount(){
        double amount = 0;
        do {
            try {
                amount = in.nextDouble();
            } catch (NumberFormatException e){
                System.out.println("This is not a proper number");
            }
        } while (amount <= 0);

        return amount;
    }

    long getID(){
        long id = -1;
        System.out.print("Please enter your account's ID: ");
        do {
            try {
                id = in.nextLong();
            } catch (NumberFormatException e){
                System.out.println("Invalid input please try again");
            }
        } while (id < 1);
        return id;
    }

    void accountTypeMenu() {
        System.out.println("Please enter the type of account you want to Access");
        System.out.println("\t1)Simple Deposit Account");
        System.out.println("\t2)Overdraft Account");
        System.out.println("\t3)Joint Account");
        System.out.println("\t4)Joint Overdraft Account");
        System.out.print("Please enter a number that corresponds to an option: ");
    }

    UserDTO getHolderInfo(){
        System.out.println("Please enter holder's firstname");
        String firstname = in.next();

        System.out.println("Please enter holder's lastname");
        String lastname = in.next();

        System.out.println("Please enter holder's ssn");
        String ssn = getSsn();

        UserDTO holder = new UserDTO(firstname, lastname, ssn);
        return holder;
    }
}
