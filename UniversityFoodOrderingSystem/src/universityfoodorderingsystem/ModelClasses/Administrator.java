package universityfoodorderingsystem.ModelClasses;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

    public Administrator(String username, String Password, String name, String addedBy) {
        super(username, Password, name, addedBy);
    }

    @Override
    public String toString() {
        return super.toString()+"\n";  // Assuming no additional fields for Administrator
    }
}
