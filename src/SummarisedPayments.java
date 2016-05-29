import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SummarisedPayments {
    private HashMap<String, Float> summarisedPayments;

    public SummarisedPayments(){
        summarisedPayments = new HashMap<String, Float>();
    }

    public void add (PaymentRecord payment)
    {
        if (summarisedPayments.containsKey(payment.getName())) {
            summarisedPayments.put(payment.getName(), summarisedPayments.get(payment.getName()) + payment.getPaymentAmount());
        }
        else
        {
            summarisedPayments.put(payment.getName(),payment.getPaymentAmount());
        }
    }

    public ArrayList<PersonBalance> getAllUnderpayments()
    {
        float averagePayment = averagePayment();
        ArrayList<PersonBalance> result = new ArrayList<PersonBalance>();
        for (Map.Entry<String, Float> payment : summarisedPayments.entrySet())
        {
          if (isUnderpayment(payment.getValue(), averagePayment)){
              result.add(new PersonBalance(payment.getKey(),payment.getValue() - averagePayment));
          }
        }
        return result;
    }

    public ArrayList<PersonBalance> getAllOverpayments()
    {
        float averagePayment = averagePayment();
        ArrayList<PersonBalance> result = new ArrayList<PersonBalance>();
        for (Map.Entry<String, Float> payment : summarisedPayments.entrySet())
        {
            if (isOverpayment(payment.getValue(), averagePayment)){
                result.add(new PersonBalance(payment.getKey(),payment.getValue() - averagePayment));
            }
        }
        return result;
    }

    public ArrayList<PersonBalance> getAll()
    {
        ArrayList<PersonBalance> result = new ArrayList<PersonBalance>();
        for (Map.Entry<String,Float> payment : summarisedPayments.entrySet())
        {
            result.add(new PersonBalance(payment.getKey(),payment.getValue()));
        }
        return result;
    }

    public float averagePayment (){
        float result = (sum() / summarisedPayments.size() * 100);
        result = Math.round(result);
        return result/100;
    }

    public int size ()
    {
        return summarisedPayments.size();
    }

    private boolean isOverpayment(float paymentAmount, float averagePayment){
        return (paymentAmount-averagePayment > 0);
    }

    private boolean isUnderpayment(float paymentAmount, float averagePayment){
        return (paymentAmount-averagePayment < 0);
    }

    public float sum(){
        float sum = 0;
        for (Map.Entry<String,Float> payment: summarisedPayments.entrySet())
        {
            sum += payment.getValue();
        }
        return sum;
    }

    //for debugging purpose
    private void show(){
        for (Map.Entry<String, Float> payment : summarisedPayments.entrySet())
        {
            System.out.println(payment.getKey() + "  " + payment.getValue());
        }
    }
}
