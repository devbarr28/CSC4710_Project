public class statistics {
    private String clientName;
    private int totalTrees;
    private double totalDueAmount;
    private double totalPaidAmount;
    private String lastWorkDate;

   
    public statistics() {
    }

    public statistics(String clientName, int totalTrees, double totalDueAmount, double totalPaidAmount, String lastWorkDate) {
        this.clientName = clientName;
        this.totalTrees = totalTrees;
        this.totalDueAmount = totalDueAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.lastWorkDate = lastWorkDate;
    }

    
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getTotalTrees() {
        return totalTrees;
    }

    public void setTotalTrees(int totalTrees) {
        this.totalTrees = totalTrees;
    }

    public double getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(double totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public String getLastWorkDate() {
        return lastWorkDate;
    }

    public void setLastWorkDate(String lastWorkDate) {
        this.lastWorkDate = lastWorkDate;
    }
}

