package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/bank";
    private static final String username = "root";
    private static final String password = "";
    private static Connection connection;
    public static Connection getConnection() {

        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url,username,password);
                System.out.println("connexion est bien etablir");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error lors de la connexion");
            }
        }
        return connection;
    }
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
