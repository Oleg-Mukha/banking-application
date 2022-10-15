package database.dao;

import data.Transaction;

import java.util.ArrayList;

public interface TransactionsDAO {
    ArrayList<Transaction> showTransactions();
    String createTransaction(Transaction transaction);
    String getAmount();
}
