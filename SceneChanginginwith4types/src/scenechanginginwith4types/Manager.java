package scenechanginginwith4types;

public class Manager {

    String name;
    private String pass;
    String uname;
    final String type = "Manager";

    public Manager(String name, String pass, String uname) {
        this.name = name;
        this.pass = pass;
        this.uname = uname;
    }

    protected boolean checkPass(String p) {

        if (this.pass.equals(p)) {
            return true;
        } else {
            return false;
        }
    }

    public String getType() {
        return type;
    }

}
