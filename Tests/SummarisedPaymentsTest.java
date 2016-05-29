import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SummarisedPaymentsTest {

    ArrayList<PaymentRecord> payments = new ArrayList<>();
    SummarisedPayments summarisedPayments = new SummarisedPayments();

    @Test
    public void testAdd() throws Exception {
        init();
        for (PaymentRecord paymentRecord: payments)
        {
            summarisedPayments.add(paymentRecord);
        }
        Assert.assertTrue(summarisedPayments.size() == 3);
    }

    @Test // average = 100
    public void testGetAllUnderpayments() throws Exception {
        init();
        for (PaymentRecord paymentRecord: payments)
        {
            summarisedPayments.add(paymentRecord);
        }
        ArrayList<PersonBalance> underpayments = summarisedPayments.getAllUnderpayments();
        Assert.assertTrue(underpayments.size() == 2);
    }

    @Test
    public void testGetAllOverpayments() throws Exception {
        init();
        for (PaymentRecord paymentRecord: payments)
        {
            summarisedPayments.add(paymentRecord);
        }
        ArrayList<PersonBalance> overpayments = summarisedPayments.getAllOverpayments();
        Assert.assertTrue(overpayments.size() == 1);
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

    }
}