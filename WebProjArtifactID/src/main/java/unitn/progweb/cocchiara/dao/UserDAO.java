package unitn.progweb.cocchiara.dao;

import unitn.progweb.cocchiara.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends BasicDAO {

    /*


INSERT INTO account (username,hashpass,type)
VALUES ('Marco',crypt('PassOne', gen_salt('bf', 8)),'PT'),
('Paolo',crypt('PasswordPaolo', gen_salt('bf', 8)),'DC')


SELECT username,type FROM account WHERE username='Marco' AND hashpass=crypt('PassOne', hashpass)
     */

    public ArrayList<User> getUsers() {
        String query = "SELECT username,type FROM account";
        Connection conn = startConnection();
        Statement stmt = readyBasicStatement(conn);
        ArrayList<User> out = new ArrayList<User>();
        try {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()) {
                String username = results.getString(1);
                String type = results.getString(2);
                out.add(new User(username,type));
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
        String query = "SELECT username,type FROM account WHERE username=? AND hashpass=crypt(?, hashpass)";
        Connection conn = startConnection();
        User out = new User();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
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
