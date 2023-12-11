package universityfoodorderingsystem.ModelClasses;

import java.io.Serializable;

public class Customer extends User implements Serializable {

    private String CustomerAddress;
    float credit;

    public Customer(String CustomerAddress, float credit, String username, String Password, String name, String addedBy) {
        super(username, Password, name, addedBy);
        this.CustomerAddress = CustomerAddress;
        this.credit = credit;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String CustomerAddress) {
        this.CustomerAddress = CustomerAddress;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return super.toString() + "<> CustomerAddress=" + CustomerAddress + "<> credit=" + credit + '\n';
    }

    public void getCustomerAddressProperty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
