package universityfoodorderingsystem.ModelClasses;

public class DeliveryRunner extends User {

    public DeliveryRunner(String username, String Password, String name, String addedBy) {
        super(username, Password, name, addedBy);
    }

    @Override
    public String toString() {
        return super.toString() + "\n";  
    }
}
