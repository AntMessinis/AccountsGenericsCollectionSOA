package dto;

import dao.AbstractDAO;

public abstract class AbstractDTO {
    UserDTO holder;
    String iban;
    double balance;

    public AbstractDTO(UserDTO holder, String iban, double balance) {
        this.holder = new UserDTO(holder);
        this.iban = iban;
        this.balance = balance;
    }

    AbstractDTO(AbstractDTO dto){
        holder = dto.getHolder();
        iban = dto.getIban();
        balance = dto.getBalance();
    }

    public AbstractDTO() {
    }

    public UserDTO getHolder() {
        return holder;
    }

    public void setHolder(UserDTO holder) {
        this.holder = new UserDTO(holder);
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
