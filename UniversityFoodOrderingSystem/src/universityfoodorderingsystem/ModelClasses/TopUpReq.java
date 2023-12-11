package universityfoodorderingsystem.ModelClasses;

import java.io.Serializable;

public class TopUpReq implements Serializable {

    int id;
    String name, customerUsername;
    float ammount;

    public TopUpReq(int id, String customerUsername, String name, float ammount) {
        this.id = id;
        this.customerUsername = customerUsername;
        this.name = name;
        this.ammount = ammount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public float getAmmount() {
        return ammount;
    }

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }

    @Override
    public String toString() {
        return "TopUpReq{" + "id=" + id + ", customerUsername=" + customerUsername + ", name=" + name + ", ammount=" + ammount + '}';
    }

    public String writable() {
        return "id=" + id + "<> customerUsername=" + customerUsername + "<> name=" + name + "<> ammount=" + ammount + "\n";

    }

}
