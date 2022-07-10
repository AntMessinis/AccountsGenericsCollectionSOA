package model;

public class Account extends AbstractAccount{

    public Account(){}

    public Account(User holder, double balance, String iban) {
        super(holder, balance, iban);
    }

    public Account(Account account){
        super(account);
    }

    @Override
    public String toString() {
        return "Account Details{" +
                "Account ID= '" + super.getId() + '\'' +
                ",Iban='" + iban + '\'' +
                ", Holder='" + holder + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
