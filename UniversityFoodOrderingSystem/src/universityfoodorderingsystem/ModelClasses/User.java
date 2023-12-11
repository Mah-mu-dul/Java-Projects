package universityfoodorderingsystem.ModelClasses;

public class User {

    private String username, Password, name, addedBy;

    public User(String username, String Password, String name, String addedBy) {
        this.username = username;
        this.Password = Password;
        this.name = name;
        this.addedBy = addedBy;
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

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public String toString() {
        return "username=" + username + "<> Password=" + Password + "<> name=" + name + "<> addedBy=" + addedBy;
    }

}
