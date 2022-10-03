import database.TransactionServiceMySQL;

public class Main {
    public static void main(String[] args) {
        TransactionServiceMySQL transactionServiceMySQL = new TransactionServiceMySQL();
        System.out.println(transactionServiceMySQL.showTransactions());
    }
}