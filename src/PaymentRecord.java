public class PaymentRecord {
    private String name;
    private String service;
    private float paymentAmount;

    public PaymentRecord(String name, String service, float paymentAmount)
    {
        this.name = name;
        this.service = service;
        this.paymentAmount = paymentAmount;
    }

    public String getName() {
        return name;
    }

    public String getService() {
        return service;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }
}
