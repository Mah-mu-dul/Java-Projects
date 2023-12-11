package scenechanginginwith4types;

public class Stuff {

    String name;
    private String pass;
    String uname;
    final String type = "Stuff";

    public Stuff(String name, String pass, String uname) {
        this.name = name;
        this.pass = pass;
        this.uname = uname;
    }

    protected String getPass() {
        return pass;
    }

    public String getType() {
        return type;
    }

}
