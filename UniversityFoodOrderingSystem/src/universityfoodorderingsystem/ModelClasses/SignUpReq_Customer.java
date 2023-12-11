package universityfoodorderingsystem.ModelClasses;

public class SignUpReq_Customer {

    private String username, Password, name, CustomerAddress;

    public SignUpReq_Customer(String username, String Password, String name, String CustomerAddress) {
        this.username = username;
        this.Password = Password;
        this.name = name;
        this.CustomerAddress = CustomerAddress;
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

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String CustomerAddress) {
        this.CustomerAddress = CustomerAddress;
    }

    @Override
    public String toString() {
        return "username=" + username + ", Password=" + Password + ", name=" + name + ", CustomerAddress=" + CustomerAddress + "\n";
    }

    public Customer toCustomer(String addedBy) {
        return new Customer(CustomerAddress, 00, username, Password, name, addedBy);
    }
}
