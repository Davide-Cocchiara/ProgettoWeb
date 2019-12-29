package unitn.progweb.cocchiara.dao;

import unitn.progweb.cocchiara.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends BasicDAO {

    public ArrayList<User> getUsers() {
        String query = "SELECT * FROM users";
        Connection conn = startConnection();
        Statement stmt = readyBasicStatement(conn);
        ArrayList<User> out = new ArrayList<User>();
        try {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()) {
                String username = results.getString(1);
                String password = results.getString(2);
                out.add(new User(username,password));
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Impossible to get the users: " + ex.getMessage());
        }
        return out;
    }

    public User getUserCred(String username,String password) {
        String query = "SELECT * FROM users WHERE username=";
        Connection conn = startConnection();
        User out = new User();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * from users where username = ? AND password= ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet results = stmt.executeQuery();
            if (results.next())
            {
                out = new User(results.getString(1),results.getString(2));
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Impossible to search for the user: " + ex.getMessage());
        }
        return out;
    }
}
