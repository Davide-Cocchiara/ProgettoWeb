package unitn.progweb.cocchiara.model;

public class User {
    public User() {
        this.username="";
        this.type="";
    }
    public User(String username,String type) {
        this.username=username;
        this.type=type;
    }
    String username;
    public String getUsername() {
        return username;
    }
    private void setUsername(String username) {
        this.username = username;
    }
    String type;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
