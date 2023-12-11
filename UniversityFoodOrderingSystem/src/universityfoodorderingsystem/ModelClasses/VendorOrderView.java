package universityfoodorderingsystem.ModelClasses;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import universityfoodorderingsystem.Scenes.Customer_ShowAllProductsController;

public class VendorOrderView {

    int orderId, productId, stockQuantity, quantity;
    String productName, vendorId, customerName, orderStatus;
    float unitPrice, totalPrice;

    public VendorOrderView(int orderId, int productId, int quantity, String vendorId, String customerName, String orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.vendorId = vendorId;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
        getValueFonProductFile();
        this.totalPrice = this.unitPrice * this.quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "orderId=" + orderId + ", productId=" + productId + ", stockQuantity=" + stockQuantity + ", quantity=" + quantity + ", productName=" + productName + ", vendorId=" + vendorId + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + ", customerName=" + customerName + ", orderStatus=" + orderStatus + '\n';
    }

    private void getValueFonProductFile() {
        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("Products.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("<>");

                    Product p = new Product(Integer.parseInt(tokens[0].split("=")[1]),
                            Integer.parseInt(tokens[1].split("=")[1]),
                            tokens[2].split("=")[1],
                            tokens[3].split("=")[1],
                            tokens[4].split("=")[1],
                            Float.parseFloat(tokens[5].split("=")[1])
                    );
                    if (p.getProductId() == this.productId) {
                        this.stockQuantity = p.getStockQuantity();
                        this.productName = p.getName();
                        this.unitPrice = p.getPrice();

                    }

                }
            } else {
            }
        } catch (IOException ex) {
        } finally {
        }
    }

}
