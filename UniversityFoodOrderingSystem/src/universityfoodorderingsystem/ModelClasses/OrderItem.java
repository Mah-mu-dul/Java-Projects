package universityfoodorderingsystem.ModelClasses;

public class OrderItem {

    String Productname;
    int productid, quantity;
    Float unitPrice;

    public OrderItem(String Productname, int productid, int quantity, Float unitPrice) {
        this.Productname = Productname;
        this.productid = productid;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String Productname) {
        this.Productname = Productname;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Productname=" + Productname + ", productid=" + productid + ", quantity=" + quantity + ", unitPrice=" + unitPrice + '\n';
    }

    public float getTotalPrice() {
        return getUnitPrice() * getQuantity(); // Assuming you have getUnitPrice and getQuantity methods
    }

}
