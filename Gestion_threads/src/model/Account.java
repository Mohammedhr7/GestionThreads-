package model;

import java.math.BigDecimal;

public class Account {
    private String accountNumber;
    private String accountHolder;
    private Person balance;
    private static int nextAccountNumber = 1;
    public Account() {
        this.accountNumber = "ACC" + nextAccountNumber++;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Getters et setters (à ajouter selon les besoins)
    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
  public  void setAccountNumber(int accountNumber){
        this.accountNumber= String.valueOf(accountNumber);
  }


}
