package config;

import java.sql.Date;

public class Config {
    // Database connection data
    public static final String USER = "root";
    public static final String PASSWORD = "6971";
    public static final String DB_HOST = "localhost";
    public static final String DB_PORT = "3306";
    public static final String DB_NAME = "db_banking";

    // Table fields 'transactions'
    public static final String DATE = "date";
    public static final String DIRECTION = "transaction_direction";
    public static final String SENDER = "sender_card";
    public static final String RECEIVER = "receiver_card";
    public static final String SUM = "transaction_amount";
    public static final String CURRENT_AMOUNT = "current_balance";

    // Current date
    public static final Date CURRENT_DATE = new Date(System.currentTimeMillis());
}
