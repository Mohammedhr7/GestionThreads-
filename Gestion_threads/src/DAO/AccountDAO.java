package DAO;

import model.Account;
import model.Person;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO  {
    private static final String URL = "jdbc:mysql://localhost:3306/bankss";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";


    public void createAccount(Person accountHolder, BigDecimal initialBalance) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String insertAccountHolderQuery = "INSERT INTO account_holder (first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?)";
            String insertAccountQuery = "INSERT INTO account (account_holder_id, balance) VALUES (?, ?)";

            try (PreparedStatement accountHolderStatement = connection.prepareStatement(insertAccountHolderQuery, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement accountStatement = connection.prepareStatement(insertAccountQuery)) {

                connection.setAutoCommit(false);

                // Insert account holder
                accountHolderStatement.setString(1, accountHolder.getFirstName());
                accountHolderStatement.setString(2, accountHolder.getLastName());
                accountHolderStatement.setString(3, accountHolder.getEmail());
                accountHolderStatement.setString(4, accountHolder.getPhoneNumber());

                accountHolderStatement.executeUpdate();

                // Get generated account holder ID
                ResultSet generatedKeys = accountHolderStatement.getGeneratedKeys();
                generatedKeys.next();
                int accountHolderId = generatedKeys.getInt(1);

                // Insert account
                accountStatement.setInt(1, accountHolderId);
                accountStatement.setBigDecimal(2, initialBalance);

                accountStatement.executeUpdate();

                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Account getAccountByNumber(String accountNumber) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String selectAccountQuery = "SELECT * FROM account WHERE account_number = ?";

            try (PreparedStatement statement = connection.prepareStatement(selectAccountQuery)) {
                statement.setString(1, accountNumber);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Account account = new Account();
                    // Populate account data from ResultSet
                    account.setAccountNumber(Integer.parseInt(resultSet.getString("account_number")));
                    // ... (populate other fields)

                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String selectAllAccountsQuery = "SELECT * FROM account";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectAllAccountsQuery)) {

                while (resultSet.next()) {
                    Account account = new Account();
                    // Populate account data from ResultSet
                    account.setAccountNumber(Integer.parseInt(resultSet.getString("account_number")));
                    // ... (populate other fields)

                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    public void updateAccountBalance(String accountNumber, BigDecimal newBalance) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String updateBalanceQuery = "UPDATE account SET balance = ? WHERE account_number = ?";

            try (PreparedStatement statement = connection.prepareStatement(updateBalanceQuery)) {
                statement.setBigDecimal(1, newBalance);
                statement.setString(2, accountNumber);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteAccountByNumber(String accountNumber) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String deleteAccountQuery = "DELETE FROM account WHERE account_number = ?";

            try (PreparedStatement statement = connection.prepareStatement(deleteAccountQuery)) {
                statement.setString(1, accountNumber);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
