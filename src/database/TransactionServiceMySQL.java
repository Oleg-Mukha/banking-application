package database;

import data.Transaction;
import database.dao.TransactionsDAO;
import database.queries.Queries;

import java.sql.*;
import java.util.ArrayList;

public class TransactionServiceMySQL implements TransactionsDAO {

    private Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_banking", "root", "6971");
        } catch (ClassNotFoundException var3) {
            System.out.println("Driver not loaded!");
            var3.printStackTrace();
        } catch (SQLException exception) {
            System.out.println("Connection not established!");
            exception.printStackTrace();
        }

        return connection;
    }

    private static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        }

    }

    private static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        }
    }


    public ArrayList<Transaction> showTransactions() {
        Connection connection = null;
        Statement statement = null;
        ArrayList<Transaction> transactionsList = new ArrayList<>();

        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.SHOW_ALL_TRANSACTIONS.getQuery());

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setCurrentDate(resultSet.getDate("date"));
                transaction.setNameTransaction(resultSet.getString("transaction_direction"));
                transaction.setSenderCard(resultSet.getString("sender_card"));
                transaction.setReceiverCard(resultSet.getString("receiver_card"));
                transaction.setSum(resultSet.getDouble("current_sum"));
                transactionsList.add(transaction);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close(connection);
            close(statement);
        }
        return transactionsList;
    }
}
