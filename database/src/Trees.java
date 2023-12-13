public class Trees extends QuoteRequest {
    private int id;
    private QuoteRequest quoteid;
    private double size;
    private double height;
    private double distanceFromHouse;

    

    public Trees() {
    }

    public Trees(QuoteRequest quoteid, double size, double height, double distanceFromHouse) {
        this.quoteid = quoteid;
        this.size = size;
        this.height = height;
        this.distanceFromHouse = distanceFromHouse;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuoteRequest getQuote() {
        return quoteid;
    }

    public void setQuote(QuoteRequest quote) {
        this.quoteid = quote;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDistanceFromHouse() {
        return distanceFromHouse;
    }

    public void setDistanceFromHouse(double distanceFromHouse) {
        this.distanceFromHouse = distanceFromHouse;
    }
}
