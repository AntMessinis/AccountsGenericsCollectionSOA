package model;

public abstract class AbstractAccount extends AbstractEntity implements IAccount{
    User holder;
    double balance;
    String iban;

    AbstractAccount(){
        super();
    }

    public AbstractAccount(User holder, double balance, String iban) {
        super();
        this.holder = new User(holder);
        this.balance = balance;
        this.iban = iban;
    }

    public AbstractAccount(AbstractAccount account) {
        super();
        holder = account.getHolder();
        balance = account.getBalance();
        iban = account.getIban();
    }

    @Override
    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
