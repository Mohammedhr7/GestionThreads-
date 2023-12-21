package model;

import model.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BankTransaction {
    private int transactionId;
    private BigDecimal amount;
    private TransactionType transactionType;
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private Timestamp transactionDate;
    private String description;
    private boolean isReversed;

    public BankTransaction() {
        // Constructeur par défaut
    }

    public BankTransaction(BigDecimal amount, TransactionType transactionType, String sourceAccountNumber, String targetAccountNumber, Timestamp transactionDate, String description, boolean isReversed) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.transactionDate = transactionDate;
        this.description = description;
        this.isReversed = isReversed;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReversed() {
        return isReversed;
    }

    public void setReversed(boolean reversed) {
        isReversed = reversed;
    }

    // Autres méthodes si nécessaires...
}
