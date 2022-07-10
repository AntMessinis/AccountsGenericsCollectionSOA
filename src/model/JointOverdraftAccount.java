package model;

public class JointOverdraftAccount extends AbstractAccount implements Joinable{
    User secondHolder;

    public JointOverdraftAccount() {
    }

    public JointOverdraftAccount(User holder, double balance, String iban, User secondHolder) {
        super(holder, balance, iban);
        this.secondHolder = new User(secondHolder);
    }

    public JointOverdraftAccount(JointOverdraftAccount account){
        super(account);
        this.secondHolder = new User(account.getSecondHolder());
    }

    @Override
    public User getSecondHolder() {
        return secondHolder;
    }

    @Override
    public void setSecondHolder(User secondHolder) {
        this.secondHolder = new User(secondHolder);
    }

    @Override
    public String toString() {
        return "JointOverdraftAccount Details{" +
                "Account ID= '" + super.getId() + '\'' +
                ", Iban='" + iban +
                ", Holder=" + holder +
                ", Second Holder=" + secondHolder +
                ", Balance=" + balance + '\'' +
                '}';
    }
}
