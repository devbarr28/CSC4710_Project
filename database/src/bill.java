public class bill {
    private int id;  // Renamed from billID to match the database
    private String status;  // Renamed from billStatus to match the database
    private int orderID;  // Renamed from quoteID to match the database
    private double price;  // Added to match the database
    private double discount;  // Added to match the database
    private double balance;  // Added to match the database
    private String note;

    public bill() {
    }

    public bill(int id, String status, int orderID, double price, double discount, double balance, String note) {
        this.id = id;
        this.status = status;
        this.orderID = orderID;
        this.price = price;
        this.discount = discount;
        this.balance = balance;
        this.note = note;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
