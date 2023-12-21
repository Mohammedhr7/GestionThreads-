import Controlleur.BankController;
import DAO.AccountDAO;
import DAO.IAccountDAO;
import model.TransactionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class BankAppGUI extends JFrame {
    private BankController bankController;

    private JTextField clientNameField;
    private JTextField accountNumberField;
    private JTextField amountField;
    private JTextArea logTextArea;

    public BankAppGUI(BankController bankController) {
        this.bankController = bankController;

        setTitle("Bank Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel clientNameLabel = new JLabel("Client Name:");
        clientNameField = new JTextField();
        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton logTransactionButton = new JButton("Log Transaction");

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);

        panel.add(clientNameLabel);
        panel.add(clientNameField);
        panel.add(accountNumberLabel);
        panel.add(accountNumberField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(transferButton);
        panel.add(logTransactionButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(logTextArea), BorderLayout.CENTER);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction(TransactionType.DEPOT);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction(TransactionType.RETRAIT);
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction(TransactionType.TRANSFERT);
            }
        });

        logTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logTransactionDetails();
            }
        });
    }

    private void performTransaction(TransactionType type) {
        String clientName = clientNameField.getText();
        String accountNumber = accountNumberField.getText();
        BigDecimal amount = new BigDecimal(amountField.getText());

        bankController.performTransaction(amount, type, accountNumber, "2"); // Hardcoded recipient for simplicity
        updateLog("Transaction: " + type + " - Amount: " + amount + " - Client: " + clientName);
    }

    private void logTransactionDetails() {
        String transactionDetails = "Transaction Log:\n";
        transactionDetails += logTextArea.getText();

        try (PrintWriter writer = new PrintWriter(new FileWriter("transaction_log.txt", true))) {
            writer.println(transactionDetails);
            writer.println("------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLog(String logMessage) {
        logTextArea.append(logMessage + "\n");
    }


}
