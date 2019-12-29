package unitn.progweb.cocchiara.model;

public class User {
    public User() {
        this.username="";
        this.password="";
    }
    public User(String username,String password) {
        this.username=username;
        this.password=password;
    }
    String username;
    public String getUsername() {
        return username;
    }
    private void setUsername(String username) {
        this.username = username;
    }

    String password;
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

}
