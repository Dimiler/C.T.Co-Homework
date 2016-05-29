public class PersonBalance{
    private String name;
    private float amount;
    public PersonBalance(String name, float amount)
    {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
