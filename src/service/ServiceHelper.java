package service;

import dto.AbstractDTO;
import dto.JoinableDTO;
import model.User;

public class ServiceHelper<D extends AbstractDTO> {
    User extractHolder(D dto){
        String firstname = dto.getHolder().getFirstname();
        String lastname = dto.getHolder().getLastname();
        String ssn = dto.getHolder().getSsn();

        User holder = new User(firstname, lastname, ssn);
        return holder;
    }

    String extractIban(D dto){
        String iban = dto.getIban();
        return iban;
    }

    double extractBalanceAmount(D dto){
        double balance = dto.getBalance();
        return balance;
    }

    boolean validateSsn(String ssn, User holder){
        if (ssn == null) return false;
        return holder.getSsn().equals(ssn);
    }

    User extractSecondHolder(JoinableDTO dto){
        String firstname = dto.getSecondHolder().getFirstname();
        String lastname = dto.getSecondHolder().getLastname();
        String ssn = dto.getSecondHolder().getSsn();

        User secondHolder = new User(firstname, lastname, ssn);
        return secondHolder;
    }

}
