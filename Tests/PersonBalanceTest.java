import org.junit.*;
import org.junit.Test;

public class PersonBalanceTest {
    private PersonBalance personBalance = new PersonBalance("tom", 100);

    @org.junit.Test
    public void testGetName() throws Exception {
        Assert.assertEquals(personBalance.getName(),"tom" );

    }

    @Test
    public void testGetAmount() throws Exception {
        Assert.assertTrue(personBalance.getAmount() == 100);
    }

    @Test
    public void testSetAmount() throws Exception {
        personBalance.setAmount(10);
        Assert.assertTrue(personBalance.getAmount() == 10);
    }
}