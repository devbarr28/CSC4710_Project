import java.io.Serializable;

public class CounterRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private int quoteId;
    private String counterNotes;
    private double counterPrice;

    public CounterRequest() {
    }

    public CounterRequest(int quoteId, String counterNotes, double counterPrice) {
        this.quoteId = quoteId;
        this.counterNotes = counterNotes;
        this.counterPrice = counterPrice;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public String getCounterNotes() {
        return counterNotes;
    }

    public void setCounterNotes(String counterNotes) {
        this.counterNotes = counterNotes;
    }

    public double getCounterPrice() {
        return counterPrice;
    }

    public void setNewPrice(double counterPrice) {
        this.counterPrice = counterPrice;
    }
}
