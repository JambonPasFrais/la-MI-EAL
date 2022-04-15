public class Invoice {
    private int amount;
    private String details;
    Invoice(){
        this.amount = 0;
        this.details ="";
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getAmount() {
        return amount;
    }

    public String getDetails() {
        return details;
    }
}
