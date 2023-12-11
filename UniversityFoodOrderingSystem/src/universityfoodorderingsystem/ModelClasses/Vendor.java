package universityfoodorderingsystem.ModelClasses;

import java.io.Serializable;

public class Vendor extends User implements Serializable {

    String shoopName;

    public Vendor(String shoopName, String username, String Password, String name, String addedBy) {
        super(username, Password, name, addedBy);
        this.shoopName = shoopName;
    }

    public String getShoopName() {
        return shoopName;
    }

    public void setShoopName(String shoopName) {
        this.shoopName = shoopName;
    }

    @Override
    public String toString() {
        return super.toString() + "<> shoopName=" + shoopName + "\n";
    }

}
