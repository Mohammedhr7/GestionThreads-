import model.Account;

import java.math.BigDecimal;

public class BankThreads extends Thread {
    private String clientName;
    private Account account;

    public BankThreads(String clientName, Account account) {
        this.clientName = clientName;
        this.account = account;
    }

    @Override
    public void run() {
        // Simuler des opérations bancaires
        deposit(BigDecimal.valueOf(200));
        withdraw(BigDecimal.valueOf(100));
        transfer(BigDecimal.valueOf(50), new Account());
    }

    // Méthode de dépôt (synchronisée pour éviter les problèmes de concurrence)
    private synchronized void deposit(BigDecimal amount) {
        System.out.println(clientName + " depositing " + amount + " into account " + account.getAccountNumber());
        BigDecimal currentBalance = account.getBalance();
        account.setBalance(currentBalance.add(amount));
        System.out.println("New balance after deposit: " + account.getBalance());
    }

    // Méthode de retrait (synchronisée pour éviter les problèmes de concurrence)
    private synchronized void withdraw(BigDecimal amount) {
        System.out.println(clientName + " withdrawing " + amount + " from account " + account.getAccountNumber());
        BigDecimal currentBalance = account.getBalance();
        if (currentBalance.compareTo(amount) >= 0) {
            account.setBalance(currentBalance.subtract(amount));
            System.out.println("New balance after withdrawal: " + account.getBalance());
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    // Méthode de transfert (synchronisée pour éviter les problèmes de concurrence)
    private synchronized void transfer(BigDecimal amount, Account recipientAccount) {
        System.out.println(clientName + " transferring " + amount + " from account " + account.getAccountNumber() +
                " to account " + recipientAccount.getAccountNumber());

        BigDecimal currentBalance = account.getBalance();
        if (currentBalance.compareTo(amount) >= 0) {
            account.setBalance(currentBalance.subtract(amount));
            recipientAccount.setBalance(recipientAccount.getBalance().add(amount));
            System.out.println("New balance after transfer: " + account.getBalance());
            System.out.println("Recipient's new balance after transfer: " + recipientAccount.getBalance());
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}
