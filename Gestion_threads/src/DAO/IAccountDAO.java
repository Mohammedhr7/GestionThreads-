package DAO;

import model.Account;
import model.Person;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountDAO {
    void createAccount(Person accountHolder, BigDecimal initialBalance);
    Account getAccountByNumber(String accountNumber);
    List<Account> getAllAccounts();
    void updateAccountBalance(Account account);
    void deleteAccountByNumber(Account account);

    void updateAccountBalance(String accountNumber, BigDecimal newBalance);

    void deleteAccountByNumber(String accountNumber);
}