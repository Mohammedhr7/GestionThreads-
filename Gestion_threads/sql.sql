-- Create a database
CREATE DATABASE IF NOT EXISTS bank_database;
USE bank_database;

-- Create a table for account holders
CREATE TABLE IF NOT EXISTS account_holder (
                                              account_holder_id INT PRIMARY KEY AUTO_INCREMENT,
                                              first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(15)
    );

-- Create a table for accounts
CREATE TABLE IF NOT EXISTS account (
                                       account_number INT PRIMARY KEY AUTO_INCREMENT,
                                       account_holder_id INT,
                                       balance DECIMAL(10, 2),
    FOREIGN KEY (account_holder_id) REFERENCES account_holder(account_holder_id)
    );

-- Create a table for transactions
CREATE TABLE IF NOT EXISTS transaction (
                                           transaction_id INT PRIMARY KEY AUTO_INCREMENT,
                                           account_number INT,
                                           amount DECIMAL(10, 2),
    transaction_type VARCHAR(20),
    target_account_number INT,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_number) REFERENCES account(account_number),
    FOREIGN KEY (target_account_number) REFERENCES account(account_number)
    );
haka rah saybet lih database
