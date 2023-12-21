package DAO;

import model.BankTransaction;
import model.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface IBankTransactionDAO {
    void createTransaction(BigDecimal amount, TransactionType type,
                           String sourceAccountNumber, String targetAccountNumber);
    BankTransaction getTransactionById(int transactionId);
    List<BankTransaction> getAllTransactions();
    List<BankTransaction> getTransactionsByAccount(String accountNumber);
    void updateTransaction(BankTransaction transaction);
    void deleteTransaction(BankTransaction transaction);
}
