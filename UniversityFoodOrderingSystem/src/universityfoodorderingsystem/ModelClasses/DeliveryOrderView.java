package universityfoodorderingsystem.ModelClasses;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import universityfoodorderingsystem.Scenes.Customer_ShowAllProductsController;

public class DeliveryOrderView {

    int orderId, quantity, productId;
    String ShopName, productName, customerID, CustomerName, CustomerAddress, Status;
    float totalPrice;

    public DeliveryOrderView(int orderId) {

        setFromOrders(orderId);

    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String ShopName) {
        this.ShopName = ShopName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String CustomerAddress) {
        this.CustomerAddress = CustomerAddress;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void setFromProducts() {
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
                    if (Integer.parseInt(tokens[0].split("=")[1]) == this.productId) {
                        System.out.println(tokens[0].split("=")[1] + "from product" + this.productId);

                        Product p = new Product(Integer.parseInt(tokens[0].split("=")[1]),
                                Integer.parseInt(tokens[1].split("=")[1]),
                                tokens[2].split("=")[1],
                                tokens[3].split("=")[1],
                                tokens[4].split("=")[1],
                                Float.parseFloat(tokens[5].split("=")[1])
                        );
                        this.productName = p.getName();
                        this.ShopName = p.getShopname();
                        setFromCustomers();

                    }

                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

    }

    private void setFromOrders(int orderId) {
        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("Orders.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split(",");

                    if (Integer.parseInt(tokens[6].split("=")[1]) == orderId) {

                        Order o = new Order(
                                Integer.parseInt(tokens[6].split("=")[1]),
                                Integer.parseInt(tokens[0].split("=")[1]),
                                Integer.parseInt(tokens[1].split("=")[1]),
                                tokens[3].split("=")[1],
                                tokens[4].split("=")[1],
                                tokens[5].split("=")[1]
                        );
                        this.orderId = orderId;
                        this.quantity = o.getQuantity();
                        this.customerID = o.getCustomerId();
                        this.Status = o.getStatus();
                        this.totalPrice = o.getTotalPrice();
                        this.productId = o.getProductId();
                        setFromProducts();
                    }
                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    private void setFromCustomers() {
        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("Customers.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("<>");

                    if (tokens[0].split("=")[1].equals(this.customerID)) {
                        Customer c = new Customer(
                                tokens[4].split("=")[1],
                                Float.parseFloat(tokens[5].split("=")[1]),
                                tokens[0].split("=")[1],
                                "",
                                tokens[2].split("=")[1],
                                ""
                        );
                        this.CustomerName = c.getName();
                        this.CustomerAddress = c.getCustomerAddress();

                    }

                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    @Override
    public String toString() {
        return "orderId=" + orderId + ", quantity=" + quantity + ", productId=" + productId + ", ShopName=" + ShopName + ", productName=" + productName + ", customerID=" + customerID + ", CustomerName=" + CustomerName + ", CustomerAddress=" + CustomerAddress + ", Status=" + Status + ", totalPrice=" + totalPrice + '\n';
    }

}
