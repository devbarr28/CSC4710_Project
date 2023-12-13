import java.util.Date;

public class QuoteRequest extends Users {

    private int clientID;
    private int quoteID;
    private Double price;
    private String status;
    private String scheduleStart;
    private String scheduleEnd;

    // Constructors, getters, and setters
    public QuoteRequest() {
    	
    }
    public QuoteRequest(int quoteID) {
    	
    }
    
    public QuoteRequest(int clientID, Double price,String status, String scheduleStart, String scheduleEnd) {
        this.clientID = clientID;
        this.price = price;
        this.status = status;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status = status;
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
