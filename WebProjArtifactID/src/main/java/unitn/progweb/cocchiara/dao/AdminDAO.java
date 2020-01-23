package unitn.progweb.cocchiara.dao;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO extends BasicDAO {


    public Boolean isAdmin(@NotNull String codicefiscale, Connection conn) {
        if(conn == null)
            conn = startConnection();

        String query = "SELECT codicefiscale FROM admin WHERE codicefiscale=?";
        boolean out = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codicefiscale);
            ResultSet results = stmt.executeQuery();
            out = results.next();
            results.close();
            stmt.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to check if user is admin: " + ex.getMessage());
        }
        return out;
    }




}
