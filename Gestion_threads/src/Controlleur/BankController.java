package Controlleur;

import DAO.IAccountDAO;
import DAO.IBankTransactionDAO;
import model.Account;
import model.BankTransaction;
import model.Person;
import model.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public class BankController {
    private IAccountDAO accountDAO;
    private IBankTransactionDAO bankTransactionDAO;

    public BankController(IAccountDAO accountDAO, IBankTransactionDAO bankTransactionDAO) {
        this.accountDAO = accountDAO;
        this.bankTransactionDAO = bankTransactionDAO;
    }

    public void createAccount(Person accountHolder, BigDecimal initialBalance) {
        accountDAO.createAccount(accountHolder, initialBalance);
    }

    public void performTransaction(BigDecimal amount, TransactionType type, String sourceAccountNumber, String targetAccountNumber) {
        bankTransactionDAO.createTransaction(amount, type, sourceAccountNumber, targetAccountNumber);
        Account sourceAccount = accountDAO.getAccountByNumber(sourceAccountNumber);
        Account targetAccount = accountDAO.getAccountByNumber(targetAccountNumber);
        if (type == TransactionType.DEPOT) {
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
            targetAccount.setBalance(targetAccount.getBalance().add(amount));
        } else if (type == TransactionType.RETRAIT) {
            sourceAccount.setBalance(sourceAccount.getBalance().add(amount));
            targetAccount.setBalance(targetAccount.getBalance().subtract(amount));
        } else if (type == TransactionType.TRANSFERT) {
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
            targetAccount.setBalance(targetAccount.getBalance().add(amount));
        }

        accountDAO.updateAccountBalance(sourceAccount);
        accountDAO.updateAccountBalance(targetAccount);
    }

    public List<BankTransaction> getTransactionHistory(String accountNumber) {
        return bankTransactionDAO.getTransactionsByAccount(accountNumber);
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public void deleteAccount(String accountNumber) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if (account != null) {
            accountDAO.deleteAccountByNumber(account);
        }
    }
}
