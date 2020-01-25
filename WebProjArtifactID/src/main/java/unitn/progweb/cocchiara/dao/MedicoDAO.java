package unitn.progweb.cocchiara.dao;

import org.jetbrains.annotations.NotNull;
import unitn.progweb.cocchiara.model.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoDAO extends BasicDAO {



    public Medico getMedicoFromCodice(@NotNull String codiceFiscale) {

        String query = "SELECT indirizzoclinica, telefonoclinica FROM medico WHERE codicefiscale=?;";
        Connection conn = startConnection();
        Medico out = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codiceFiscale);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                String _indirizzo = results.getString(1);
                String _telefono = results.getString(2);

                out = new Medico(_indirizzo,_telefono);
            }
            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get medico from Codice Fiscale " + ex.getMessage());
        }
        return out;
    }



}
