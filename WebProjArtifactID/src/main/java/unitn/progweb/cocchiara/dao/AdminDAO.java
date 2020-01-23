package unitn.progweb.cocchiara.dao;

import unitn.progweb.cocchiara.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO extends BasicDAO {

    // TODO modify
    private Boolean isAdmin(String codicefiscale, Connection conn) {
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

    public Admin getUserCodice(String string) {
        return null;
    }

   /* public Medico getInfoMedico(String codiceFiscale) {


    }
    */

}
