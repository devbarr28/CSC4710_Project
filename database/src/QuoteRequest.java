import java.util.Date;

public class QuoteRequest extends Users {

    private int clientID;
    private int quoteID;
    private double price;
    private String scheduleStart;
    private String scheduleEnd;

    // Constructors, getters, and setters
    public QuoteRequest() {
    	
    }
    
    public QuoteRequest(String clientIDString, String priceString, String scheduleStart, String scheduleEnd) {
        this.clientID = Integer.parseInt(clientIDString);
        this.price = Double.parseDouble(priceString);
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
    }



	public int getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(String scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public String getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(String scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

}
