import Controlleur.BankController;
import DAO.AccountDAO;

import DAO.BankTransactionDAO;
import DAO.IAccountDAO;
import DAO.IBankTransactionDAO;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

            IAccountDAO accountDAO = (IAccountDAO) new AccountDAO();
            IBankTransactionDAO bankTransactionDAO = new BankTransactionDAO();

            BankController bankController = new BankController(accountDAO, bankTransactionDAO);
            SwingUtilities.invokeLater(() -> new BankAppGUI(bankController).setVisible(true));

        }}
