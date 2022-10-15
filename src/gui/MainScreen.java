package gui;

import data.Transaction;
import database.TransactionServiceMySQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static config.Config.CURRENT_DATE;

public class MainScreen implements ActionListener, KeyListener {
    static ArrayList<Transaction> transactions = (new TransactionServiceMySQL()).showTransactions();
    static JTextArea textArea;
    static StringBuilder stringBuilder = new StringBuilder();
    static JFrame mainFrame = new JFrame("Bank | Main Menu");
    JLayeredPane mainPane = new JLayeredPane();
    JLabel userCreditCard = new JLabel();
    JLabel bankName = new JLabel("Bank | Universal Bank");
    JLabel cardNumber = new JLabel("5168 7559 0703 3281");
    JLabel expirationDate = new JLabel("EXP: 10/23");
    JLabel cardHolder = new JLabel("Oleg Mukha");
    static JLabel currentBalance = new JLabel();
    JLabel balanceTitle = new JLabel("current balance");
    JLabel lastTransaction = new JLabel("recent transactions");
    JLabel description = new JLabel("[date] | [appointment] | [transaction amount]");
    ImageIcon userImage = new ImageIcon("src/images/credit-card.png");
    ImageIcon sendButton = new ImageIcon("src/images/send.png");
    JButton sendMoney = new JButton("send");
    ImageIcon frameIcon = new ImageIcon("src/images/money-bag.png");

    public MainScreen() {
        userCreditCard.setBounds(370, -50, 400, 400);
        userCreditCard.setIcon(userImage);
        userCreditCard.setBackground(Color.decode("#e9e7fe"));

        currentBalance.setFont(new Font("Roboto", Font.BOLD, 40));
        currentBalance.setBounds(50, 75, 250, 30);
        currentBalance.setForeground(Color.decode("#000000"));
        currentBalance.setBackground(Color.decode("#e9e7fe"));
        currentBalance.setText(new TransactionServiceMySQL().getAmount());
        currentBalance.setOpaque(true);

        balanceTitle.setFont(new Font("Roboto", Font.PLAIN, 20));
        balanceTitle.setBounds(50, 105, 250, 30);
        balanceTitle.setForeground(Color.decode("#000000"));
        balanceTitle.setBackground(Color.decode("#e9e7fe"));
        balanceTitle.setOpaque(true);

        sendMoney.setBackground(Color.decode("#000000"));
        sendMoney.setBounds(50, 150, 90, 70);
        sendMoney.setFocusable(false);
        sendMoney.setFocusPainted(false);
        sendMoney.setIcon(this.sendButton);
        sendMoney.addActionListener(this);
        sendMoney.setHorizontalTextPosition(0);
        sendMoney.setVerticalTextPosition(3);
        sendMoney.setFont(new Font("Roboto", Font.PLAIN, 15));
        sendMoney.setForeground(Color.decode("#FFFFFF"));

        lastTransaction.setFont(new Font("Roboto", Font.PLAIN, 20));
        lastTransaction.setBounds(200, 300, 200, 30);
        lastTransaction.setForeground(Color.decode("#000000"));
        lastTransaction.setBackground(Color.decode("#e9e7fe"));
        lastTransaction.setOpaque(true);

        description.setFont(new Font("Roboto", Font.PLAIN, 20));
        description.setBounds(10, 325, 400, 30);
        description.setForeground(Color.decode("#000000"));
        description.setBackground(Color.decode("#e9e7fe"));
        description.setOpaque(true);

        bankName.setFont(new Font("Roboto", Font.BOLD, 20));
        bankName.setBounds(380, 30, 250, 30);
        bankName.setForeground(Color.decode("#000000"));
        bankName.setBackground(Color.decode("#8768b3"));
        bankName.setOpaque(true);

        cardNumber.setFont(new Font("Courier New", Font.BOLD, 20));
        cardNumber.setBounds(380, 87, 250, 30);
        cardNumber.setForeground(Color.decode("#000000"));
        cardNumber.setBackground(Color.decode("#cbaef3"));
        cardNumber.setOpaque(true);

        expirationDate.setFont(new Font("Courier New", Font.BOLD, 20));
        expirationDate.setBounds(385, 145, 250, 30);
        expirationDate.setForeground(Color.decode("#000000"));
        expirationDate.setBackground(Color.decode("#8768b3"));
        expirationDate.setOpaque(true);

        cardHolder.setFont(new Font("Courier New", Font.PLAIN, 20));
        cardHolder.setBounds(385, 230, 250, 30);
        cardHolder.setForeground(Color.decode("#000000"));
        cardHolder.setBackground(Color.decode("#8768b3"));
        cardHolder.setOpaque(true);

        for (Transaction s : transactions) {
            stringBuilder.append(s).append("\n");
        }

        textArea = new JTextArea(stringBuilder.toString());
        textArea.setFont(new Font("Roboto", Font.PLAIN, 18));
        textArea.setBounds(10, 355, 550, 600);
        textArea.setBackground(Color.decode("#e9e7fe"));
        textArea.setForeground(Color.decode("#000000"));

        mainPane.setBounds(0, 0, 600, 700);
        mainPane.add(currentBalance);
        mainPane.add(balanceTitle);
        mainPane.add(sendMoney);
        mainPane.add(lastTransaction);
        mainPane.add(description);
        mainPane.add(textArea);
        mainPane.add(bankName);
        mainPane.add(cardNumber);
        mainPane.add(expirationDate);
        mainPane.add(cardHolder);
        mainPane.add(userCreditCard);

        mainFrame.setSize(600, 700);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setFocusable(true);
        mainFrame.getContentPane().setBackground(Color.decode("#e9e7fe"));
        mainFrame.setVisible(true);
        mainFrame.addKeyListener(this);
        mainFrame.add(mainPane);
        mainFrame.setIconImage(frameIcon.getImage());;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendMoney) {
            mainFrame.setVisible(false);
            new SendingScreen();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        currentBalance.setText(new TransactionServiceMySQL().getAmount());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    static class SendingScreen implements ActionListener, KeyListener {
        JFrame sendingFrame = new JFrame("Bank | Sending money");
        JLayeredPane mainPane = new JLayeredPane();
        JButton buttonSubmit = new JButton("Send");
        JTextField inputCardPart1 = new JTextField();
        JTextField inputCardPart2 = new JTextField();
        JTextField inputCardPart3 = new JTextField();
        JTextField inputCardPart4 = new JTextField();
        JTextField inputSum = new JTextField();
        JLabel titleForCard = new JLabel("Enter receiver card: ");
        JLabel titleForSum = new JLabel("Enter sum: ");
        JLabel icon = new JLabel();
        JLabel result = new JLabel();
        ImageIcon negativeResult = new ImageIcon("src/images/negative.gif");
        ImageIcon positiveResult = new ImageIcon("src/images/positive.gif");
        ImageIcon frameIcon = new ImageIcon("src/images/money-bag.png");
        public SendingScreen() {
            titleForCard.setBounds(40, 30, 500, 30);
            titleForCard.setFont(new Font("Roboto", Font.PLAIN, 24));
            titleForCard.setForeground(Color.decode("#000000"));
            titleForCard.setBackground(Color.decode("#e9e7fe"));

            inputCardPart1.setBounds(40, 70, 70, 30);
            inputCardPart1.setFont(new Font("Roboto", Font.PLAIN, 24));
            inputCardPart2.setBounds(130, 70, 70, 30);
            inputCardPart2.setFont(new Font("Roboto", Font.PLAIN, 24));
            inputCardPart3.setBounds(220, 70, 70, 30);
            inputCardPart3.setFont(new Font("Roboto", Font.PLAIN, 24));
            inputCardPart4.setBounds(310, 70, 70, 30);
            inputCardPart4.setFont(new Font("Roboto", Font.PLAIN, 24));

            titleForSum.setBounds(40, 110, 500, 30);
            titleForSum.setFont(new Font("Roboto", Font.PLAIN, 24));
            titleForSum.setForeground(Color.decode("#000000"));
            titleForSum.setBackground(Color.decode("#e9e7fe"));

            inputSum.setBounds(40, 150, 500, 30);
            inputSum.setFont(new Font("Roboto", Font.BOLD, 18));

            buttonSubmit.setBounds(200, 200, 200, 30);
            buttonSubmit.setFocusable(false);
            buttonSubmit.setFocusPainted(false);
            buttonSubmit.addActionListener(this);
            buttonSubmit.setBackground(Color.decode("#000000"));
            buttonSubmit.setForeground(Color.decode("#FFFFFF"));
            buttonSubmit.setFont(new Font("Roboto", Font.PLAIN, 15));

            icon.setBounds(160, 230, 250, 250);

            result.setBounds(100, 450, 450, 30);
            result.setFont(new Font("Roboto", Font.BOLD, 18));

            mainPane.setBounds(0, 0, 600, 700);
            mainPane.addKeyListener(this);
            mainPane.add(titleForCard);
            mainPane.add(inputCardPart1);
            mainPane.add(inputCardPart2);
            mainPane.add(inputCardPart3);
            mainPane.add(inputCardPart4);
            mainPane.add(titleForSum);
            mainPane.add(inputSum);
            mainPane.add(buttonSubmit);
            mainPane.add(icon);
            mainPane.add(result);

            sendingFrame.setSize(600, 700);
            sendingFrame.setIconImage(frameIcon.getImage());
            sendingFrame.setLocationRelativeTo(null);
            sendingFrame.setResizable(false);
            sendingFrame.setFocusable(true);
            sendingFrame.getContentPane().setBackground(Color.decode("#e9e7fe"));
            sendingFrame.setVisible(true);
            sendingFrame.addKeyListener(this);
            sendingFrame.add(mainPane);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonSubmit) {
                double valueInputSum = Double.parseDouble(inputSum.getText());
                double currentBalance = Double.parseDouble(new TransactionServiceMySQL().getAmount());
                String receiverCardNumber = inputCardPart1.getText() + " " + inputCardPart2.getText() + " " + inputCardPart3.getText() + " " + inputCardPart4.getText();
                if (valueInputSum > currentBalance) {
                    icon.setIcon(negativeResult);
                    result.setText("Insufficient funds to complete the transaction");
                } else {
                    result.setBounds(150, 450, 350, 30);
                    icon.setIcon(positiveResult);
                    inputCardPart1.setFocusable(false);
                    inputCardPart2.setFocusable(false);
                    inputCardPart3.setFocusable(false);
                    inputCardPart4.setFocusable(false);
                    inputSum.setFocusable(false);
                    double newCurrentBalance = currentBalance - valueInputSum;
                    Transaction newTransaction = new Transaction(CURRENT_DATE, "sending", "5168 7559 0703 3281", receiverCardNumber, valueInputSum, newCurrentBalance);
                    new TransactionServiceMySQL().createTransaction(newTransaction);
                    result.setText("Transaction made successfully!");
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 27) {
                sendingFrame.setVisible(false);
                mainFrame.setVisible(true);
                currentBalance.setText(new TransactionServiceMySQL().getAmount());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
