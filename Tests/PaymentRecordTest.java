import org.junit.*;
import org.junit.Test;


public class PaymentRecordTest {
    PaymentRecord paymentRecord = new PaymentRecord("tom", "food", 100);
    @org.junit.Test
    public void testGetName() throws Exception {
        Assert.assertEquals(paymentRecord.getName(),"tom" );
    }

    @Test
    public void testGetService() throws Exception {
        Assert.assertTrue(paymentRecord.getService() == "food" );
    }

    @Test
    public void testGetPaymentAmount() throws Exception {
        Assert.assertTrue(paymentRecord.getPaymentAmount() == 100.0 );
    }
}