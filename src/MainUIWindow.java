import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainUIWindow extends JFrame{
    private ArrayList<PaymentRecord> paymentRecords = new ArrayList<>();
    private SummarisedPayments summarisedPayments = new SummarisedPayments();
    private ArrayList<Transaction> transactions;
    private GetListOfTransaction getListOfTransaction = new GetListOfTransaction();

    private JTextField nameTextField;
    private JTextField serviceTextField;
    private JTextField paymentAmountTextField;
    private JButton addButton;
    private JTextPane paymentsField;
    private JTextPane transactionTextField;
    private JLabel summaryLabel;
    private JLabel averageLabel;
    private JButton calculateButton;
    private JPanel rootPanel;
    private JLabel nameLabel;
    private JLabel serviceLabel;
    private JLabel paymentAmountLabel;
    private JButton clearButton;
    private JButton infoButton;
    private JTextPane expensesTextPane;
    private JLabel paymentsLabel;
    private JLabel expensesLabel;
    private JLabel transactionsLabel;

    public MainUIWindow() {
        super.setContentPane(rootPanel);
        super.setTitle("Transaction Manager");
        super.setPreferredSize(new Dimension(500,800));
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean wrongPaymentAmount = false;
                String name = nameTextField.getText().toLowerCase();
                String service = serviceTextField.getText().toLowerCase();
                float paymentAmount = 0;
                try{
                    paymentAmount = Float.valueOf(paymentAmountTextField.getText());
                }
                catch (Exception exc){
                    JOptionPane.showMessageDialog(null,"Wrong payment amount");
                    wrongPaymentAmount = true;
                }
                if (!wrongPaymentAmount) {
                    paymentRecords.add(new PaymentRecord(name, service, paymentAmount));
                    summarisedPayments.add(new PaymentRecord(name, service, paymentAmount));
                    paymentsField.setText(paymentsField.getText() + name + " : " + service + " - " + paymentAmount +"\n");
                }

            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<PersonBalance> payments = summarisedPayments.getAll();
                expensesTextPane.setText("");
                for (PersonBalance payment: payments)
                {
                    expensesTextPane.setText(expensesTextPane.getText() + payment.getName() + " : " + payment.getAmount() + "\n");
                }
                transactions = getListOfTransaction.execute(summarisedPayments.getAllOverpayments(),summarisedPayments.getAllUnderpayments());
                transactionTextField.setText("");
                for (Transaction transaction: transactions) {
                    transactionTextField.setText(transactionTextField.getText() + transaction.toString());
                }
                summaryLabel.setText("Summary is : " + summarisedPayments.sum());
                averageLabel.setText("For mate : " + summarisedPayments.averagePayment());
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transactions = null;
                summarisedPayments = new SummarisedPayments();
                paymentRecords = new ArrayList<PaymentRecord>();
                paymentsField.setText("");
                transactionTextField.setText("");
                summaryLabel.setText("Summary is : " );
                averageLabel.setText("For mate : " );
                expensesTextPane.setText("");

                nameTextField.setText("");
                serviceTextField.setText("");
                paymentAmountTextField.setText("");

            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Program store data in lowercase\n" +
                        "Program rounds transactions to 2 digits after comma\n" +
                        "Write data in text boxes and press add\n" +
                        "After you finished adding data press calculate\n" +
                        "To clear all data press button clear\n" +
                        "Form is resizable");
            }
        });
    }
}
