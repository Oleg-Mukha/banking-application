package database.queries;

public enum Queries {
    SHOW_ALL_TRANSACTIONS("SELECT date, transaction_direction, sender_card, receiver_card, current_sum FROM transactions");

    public String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
