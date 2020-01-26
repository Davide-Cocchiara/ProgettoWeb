package unitn.progweb.cocchiara.dao;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class SistemaProvincialeDAO extends BasicDAO {
    public LinkedHashMap<String, String> getListaMediciFromPronvincia(String provincia) {
        LinkedHashMap<String,String>  out = new LinkedHashMap<String,String>();
        String query = "SELECT medico.codicefiscale,persona.nome,persona.cognome FROM medico JOIN persona on medico.codicefiscale=persona.codicefiscale WHERE provincia=? ORDER BY persona.cognome,persona.nome ASC";
        Connection conn = startConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, provincia);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                String codicefiscale = results.getString(1);
                String nome = results.getString(2);
                String cognome = results.getString(3);

                out.put(codicefiscale,cognome+ " " + nome);
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to retrive list medici of given provincia" + ex.getMessage());
        }
        return out;
    }

    public LinkedHashMap<String,String> getListaFarmaciFromProvincia(String provincia) {
        return null;
        // TODO bro
    }
    public LinkedHashMap<String,String> getListaEsamiFromProvincia(String provincia) {
        return null;
        // TODO bro
    }
}
