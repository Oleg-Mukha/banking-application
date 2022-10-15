package queries;

public class Queries {
    public static final String SHOW_TRANSACTIONS = "SELECT date, transaction_direction, sender_card, receiver_card, transaction_amount, current_balance FROM transactions";
    public static final String CREATE_TRANSACTION = "INSERT INTO transactions(date, transaction_direction, sender_card, receiver_card, transaction_amount, current_balance) VALUES(?, ?, ?, ?, ?, ?)";
    public static final String GET_BALANCE = "SELECT current_balance FROM transactions";

}
