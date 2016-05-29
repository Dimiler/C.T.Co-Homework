public class Transaction {
    String from;
    String to;
    float amount;

    public Transaction(String from, String to, float amount)
    {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public String toString() {
        float roundedAmount = amount * 100;
        roundedAmount = Math.round(roundedAmount);
        roundedAmount /= 100;
        return (from + " -> " +  to + " : " + roundedAmount + "\n");
    }
}
