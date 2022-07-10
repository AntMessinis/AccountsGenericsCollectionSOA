package model;

public class OverdraftAccount extends AbstractAccount{
    public OverdraftAccount(){}

    public OverdraftAccount(User holder, double balance, String iban) {
        super(holder, balance, iban);
    }

    public OverdraftAccount(OverdraftAccount account){
        super(account);
    }

    @Override
    public String toString() {
        return "OverdraftAccount Details{" +
                "Account ID= '" + super.getId() + '\'' +
                ", Iban='" + iban + '\'' +
                ", Holder=" + holder + '\'' +
                ", Balance=" + balance + '\'' +
                '}';
    }
}
