package universityfoodorderingsystem.ModelClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import universityfoodorderingsystem.Scenes.Customer_ShowAllProductsController;

public class Order {

    int orderid = 1001;
    int productId, quantity;
    String customerId, status, shopName, deliveryType, productName;
    float totalPrice;

    public Order(int orderid, int productId, int quantity, String customerId, String status, String deliveryType) {
        this.productId = productId;
        this.quantity = quantity;
        this.customerId = customerId;
        this.status = status;
        this.deliveryType = deliveryType;
        setshop();
        this.orderid = orderid;

    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryTypeCombo() {
        return deliveryType;
    }

    public int getOrderid() {
        return orderid;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "productId=" + productId + ", quantity=" + quantity + ", shopName=" + shopName + ", customerId=" + customerId + ", status=" + status + ", deliveryType=" + deliveryType + ", orderid=" + orderid + '\n';
    }

    private void setshop() {
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
                        this.setShopName(p.shopname);
                        this.totalPrice = p.getPrice() * this.quantity;
                        this.productName = p.getName();
                    }

                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

    }

    public static int getLastOrderId() {
        File f = new File("Orders.txt");
        Scanner sc = null;

        try {
            if (f.exists()) {
                sc = new Scanner(f);
                int lastOrderid = 1001;

                while (sc.hasNextLine()) {
                    String str = sc.nextLine();
                    String[] tokens = str.split(",");

                    lastOrderid = Integer.parseInt(tokens[6].split("=")[1]);
                }

                if (lastOrderid != 1001) {
                    return lastOrderid;
                } else {
                    return 0;
                }
            } else {
                return 1001;
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

}
