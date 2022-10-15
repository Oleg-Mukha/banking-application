package database;

import data.Transaction;
import database.dao.TransactionsDAO;

import static config.Config.*;
import static queries.Queries.*;

import java.sql.*;
import java.util.ArrayList;


public class TransactionServiceMySQL implements TransactionsDAO {

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, USER, PASSWORD);
        } catch (ClassNotFoundException exception) {
            System.out.println("Driver not loaded!");
            exception.printStackTrace();
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

    @Override
    public ArrayList<Transaction> showTransactions() {
        Connection connection = null;
        Statement statement = null;
        ArrayList<Transaction> transactionsList = new ArrayList<>();

        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SHOW_TRANSACTIONS);
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setCurrentDate(resultSet.getDate(DATE));
                transaction.setNameTransaction(resultSet.getString(DIRECTION));
                transaction.setSenderCard(resultSet.getString(SENDER));
                transaction.setReceiverCard(resultSet.getString(RECEIVER));
                transaction.setSum(resultSet.getDouble(SUM));
                transaction.setSum(resultSet.getDouble(CURRENT_AMOUNT));
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

    @Override
    public String createTransaction(Transaction transaction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_TRANSACTION, 6);
            preparedStatement.setDate(1, (Date) transaction.getCurrentDate());
            preparedStatement.setString(2, transaction.getNameTransaction());
            preparedStatement.setString(3, transaction.getSenderCard());
            preparedStatement.setString(4, transaction.getReceiverCard());
            preparedStatement.setDouble(5, transaction.getSum());
            preparedStatement.setDouble(6, transaction.getCurrentBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close((Statement) preparedStatement);
        }
        return "Money sent successfully";
    }

    @Override
    public String getAmount() {
        Connection connection = null;
        Statement statement = null;

        String currentBalance = null;
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_BALANCE);
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setCurrentBalance(resultSet.getDouble("current_balance"));
                double curBalance = transaction.getCurrentBalance();
                currentBalance = "" + curBalance;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close(connection);
            close(statement);
        }
        return currentBalance;
    }
}
