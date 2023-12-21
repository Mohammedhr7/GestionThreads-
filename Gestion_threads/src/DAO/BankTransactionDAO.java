package DAO;

import model.BankTransaction;
import model.TransactionType;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionDAO implements IBankTransactionDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/bank_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    @Override
    public void createTransaction(BigDecimal amount, TransactionType type, String sourceAccountNumber, String targetAccountNumber) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String insertTransactionQuery = "INSERT INTO transaction (account_number, amount, transaction_type, target_account_number) VALUES (?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(insertTransactionQuery)) {
                connection.setAutoCommit(false);

                // Insert transaction
                statement.setString(1, sourceAccountNumber);
                statement.setBigDecimal(2, amount);
                statement.setString(3, type.name());
                statement.setString(4, targetAccountNumber);

                statement.executeUpdate();

                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BankTransaction getTransactionById(int transactionId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String selectTransactionQuery = "SELECT * FROM transaction WHERE transaction_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(selectTransactionQuery)) {
                statement.setInt(1, transactionId);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    BankTransaction transaction = new BankTransaction();
                    // Populate transaction data from ResultSet
                    transaction.setTransactionId(resultSet.getInt("transaction_id"));
                    transaction.setAmount(resultSet.getBigDecimal("amount"));
                    transaction.setTransactionType(TransactionType.valueOf(resultSet.getString("transaction_type")));
                    transaction.setSourceAccountNumber(resultSet.getString("account_number"));
                    transaction.setTargetAccountNumber(resultSet.getString("target_account_number"));
                    transaction.setTransactionDate(resultSet.getTimestamp("transaction_date"));

                    return transaction;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<BankTransaction> getAllTransactions() {
        List<BankTransaction> transactions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String selectAllTransactionsQuery = "SELECT * FROM transaction";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectAllTransactionsQuery)) {

                while (resultSet.next()) {
                    BankTransaction transaction = new BankTransaction();
                    // Populate transaction data from ResultSet
                    transaction.setTransactionId(resultSet.getInt("transaction_id"));
                    transaction.setAmount(resultSet.getBigDecimal("amount"));
                    transaction.setTransactionType(TransactionType.valueOf(resultSet.getString("transaction_type")));
                    transaction.setSourceAccountNumber(resultSet.getString("account_number"));
                    transaction.setTargetAccountNumber(resultSet.getString("target_account_number"));
                    transaction.setTransactionDate(resultSet.getTimestamp("transaction_date"));

                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    public List<BankTransaction> getTransactionsByAccount(String accountNumber) {
        List<BankTransaction> accountTransactions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String selectAccountTransactionsQuery = "SELECT * FROM transaction WHERE account_number = ? OR target_account_number = ?";

            try (PreparedStatement statement = connection.prepareStatement(selectAccountTransactionsQuery)) {
                statement.setString(1, accountNumber);
                statement.setString(2, accountNumber);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    BankTransaction transaction = new BankTransaction();
                    // Populate transaction data from ResultSet
                    transaction.setTransactionId(resultSet.getInt("transaction_id"));
                    transaction.setAmount(resultSet.getBigDecimal("amount"));
                    transaction.setTransactionType(TransactionType.valueOf(resultSet.getString("transaction_type")));
                    transaction.setSourceAccountNumber(resultSet.getString("account_number"));
                    transaction.setTargetAccountNumber(resultSet.getString("target_account_number"));
                    transaction.setTransactionDate(resultSet.getTimestamp("transaction_date"));

                    accountTransactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountTransactions;
    }

    @Override
    public void updateTransaction(BankTransaction transaction) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String updateTransactionQuery = "UPDATE transaction SET amount = ?, transaction_type = ?, target_account_number = ? WHERE transaction_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(updateTransactionQuery)) {
                statement.setBigDecimal(1, transaction.getAmount());
                statement.setString(2, transaction.getTransactionType().name());
                statement.setString(3, transaction.getTargetAccountNumber());
                statement.setInt(4, transaction.getTransactionId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(BankTransaction transaction) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String deleteTransactionQuery = "DELETE FROM transaction WHERE transaction_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(deleteTransactionQuery)) {
                statement.setInt(1, transaction.getTransactionId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
