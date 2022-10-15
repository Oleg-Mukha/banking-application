package data;

import java.util.Date;

public class Transaction {
    Date currentDate;
    String nameTransaction;
    String senderCard;
    String receiverCard;
    double sum;
    double currentBalance;

    public Transaction() {
    }

    public Transaction(Date currentDate, String nameTransaction, String senderCard, String receiverCard, double sum, double currentBalance) {
        this.currentDate = currentDate;
        this.nameTransaction = nameTransaction;
        this.senderCard = senderCard;
        this.receiverCard = receiverCard;
        this.sum = sum;
        this.currentBalance = currentBalance;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getNameTransaction() {
        return nameTransaction;
    }

    public void setNameTransaction(String nameTransaction) {
        this.nameTransaction = nameTransaction;
    }

    public String getSenderCard() {
        return senderCard;
    }

    public void setSenderCard(String senderCard) {
        this.senderCard = senderCard;
    }

    public String getReceiverCard() {
        return receiverCard;
    }

    public void setReceiverCard(String receiverCard) {
        this.receiverCard = receiverCard;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return "| " + currentDate + " | " + nameTransaction + " | " + sum + " | ";
    }
}
