package universityfoodorderingsystem.ModelClasses;

import java.io.Serializable;

public class VendorReq implements Serializable {

    private String username, Password, name;
    String shopName;

    public VendorReq(String username, String Password, String name, String shoopName) {
        this.username = username;
        this.Password = Password;
        this.name = name;
        this.shopName = shoopName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return "username=" + username + ", Password=" + Password + ", name=" + name + ", shopName=" + shopName + '\n';
    }

    public Vendor toVendor(String addedBy) {
        // Assuming addedBy is always a string, you might need to adjust this depending on your requirements
        return new Vendor(shopName, username, Password, name, addedBy);
    }
}
