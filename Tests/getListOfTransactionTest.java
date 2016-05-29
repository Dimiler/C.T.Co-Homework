import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class getListOfTransactionTest {

    ArrayList<PaymentRecord> payments = new ArrayList<>();
    SummarisedPayments summarisedPayments = new SummarisedPayments();
    ArrayList<PersonBalance> overpayments;
    ArrayList<PersonBalance> underpayments;

    ArrayList<Transaction> transactions;

    GetListOfTransaction getListOfTransaction = new GetListOfTransaction();

    @Test
    public void testExecute() throws Exception {
        init();
        transactions = getListOfTransaction.execute(overpayments,underpayments);

        for (Transaction transaction: transactions)
        {
            System.out.println(transaction.from + " -> " + transaction.to + " : " + transaction.amount + "$");
        }
        System.out.println(summarisedPayments.sum());

        Assert.assertTrue(transactions.size() == 4);
    }

    private void init (){

        payments.add(new PaymentRecord("Lisa", "cake", 5));
        payments.add(new PaymentRecord("Lisa", "hotel", 120));
        payments.add(new PaymentRecord("Lisa", "museum ticket", 20));
        payments.add(new PaymentRecord("Hans", "museum ticket", 20));
        payments.add(new PaymentRecord("Hans", "museum ticket", 20));
        payments.add(new PaymentRecord("Hans", "dinner", 34));
        payments.add(new PaymentRecord("Ivan", "railway tickets", 48));
        payments.add(new PaymentRecord("Ivan", "supper", 33));
        payments.add(new PaymentRecord("Peter", "lunch", 20));
        payments.add(new PaymentRecord("Peter", "plane", 13));
        payments.add(new PaymentRecord("John", "oil", 60));

        for (PaymentRecord paymentRecord: payments)
        {
            summarisedPayments.add(paymentRecord);
        }
        System.out.println(summarisedPayments.averagePayment());

        overpayments = summarisedPayments.getAllOverpayments();
        underpayments = summarisedPayments.getAllUnderpayments();

    }
}