package model;

public interface IAccount {
    User getHolder();
    double getBalance();
    void setBalance(double amount);
    String getIban();
}
