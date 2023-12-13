public class BillMessages {
    private int id;
    private int userId;
    private int billId;
    private String msgTime;
    private double price;
    private String scheduleStart;
    private String scheduleEnd;
    private String note;

    public BillMessages() {
    }

    public BillMessages(int id, int userId, int billId, String msgTime, double price,
    		String scheduleStart, String scheduleEnd, String note) {
        this.id = id;
        this.userId = userId;
        this.billId = billId;
        this.msgTime = msgTime;
        this.price = price;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.note = note;
    }

    // Getters and Setters (you can generate these using your IDE)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
