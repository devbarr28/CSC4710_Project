public class QuoteRequest {
    private String treeType;
    private String treeSize;
    private String treeHeight;
    private String location;
    private String proximityToHouse;

    public QuoteRequest() {
    }

    public QuoteRequest(String treeType, String treeSize, String treeHeight, String location, String proximityToHouse) {
        this.treeType = treeType;
        this.treeSize = treeSize;
        this.treeHeight = treeHeight;
        this.location = location;
        this.proximityToHouse = proximityToHouse;
    }

    
    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public String getTreeSize() {
        return treeSize;
    }

    public void setTreeSize(String treeSize) {
        this.treeSize = treeSize;
    }

    public String getTreeHeight() {
        return treeHeight;
    }

    public void setTreeHeight(String treeHeight) {
        this.treeHeight = treeHeight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProximityToHouse() {
        return proximityToHouse;
    }

    public void setProximityToHouse(String proximityToHouse) {
        this.proximityToHouse = proximityToHouse;
    }
}
