package model;

public class JointAccount extends AbstractAccount implements Joinable{
    User secondHolder;

    public JointAccount() {
    }

    public JointAccount(User holder, double balance, String iban, User secondHolder) {
        super(holder, balance, iban);
        this.secondHolder = new User(secondHolder);
    }

    public JointAccount(JointAccount account){
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
        return "JointAccount Details{" +
                "Account ID= '" + super.getId() + '\'' +
                ",Iban='" + iban +
                ", Holder=" + holder +
                ", Second Holder=" + secondHolder +
                ", Balance=" + balance + '\'' +
                '}';
    }
}
