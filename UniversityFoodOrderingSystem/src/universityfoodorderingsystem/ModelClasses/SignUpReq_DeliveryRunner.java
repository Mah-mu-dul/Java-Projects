package universityfoodorderingsystem.ModelClasses;

public class SignUpReq_DeliveryRunner {

    private String username, Password, name;

    public SignUpReq_DeliveryRunner(String username, String Password, String name) {
        this.username = username;
        this.Password = Password;
        this.name = name;
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

    @Override
    public String toString() {
        return "username=" + username + ", Password=" + Password + ", name=" + name + "\n";
    }

    public DeliveryRunner toRunner(String addedBy) {
        return new DeliveryRunner(username, Password, name, addedBy);
    }
}
