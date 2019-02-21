package data;

public class Order {
    private int id;
    private int shortId;
    private int size;
    private int qty;
    private int customerId;
    private String address;
    private String shipMethod;
    private String city;
    private String state;
    private String zip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShortId() {
        return shortId;
    }

    public void setShortId(int shortId) {
        this.shortId = shortId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(String shipMethod) {
        this.shipMethod = shipMethod;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
