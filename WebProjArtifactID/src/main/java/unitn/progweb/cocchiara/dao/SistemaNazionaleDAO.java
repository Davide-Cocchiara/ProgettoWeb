package unitn.progweb.cocchiara.dao;


import java.sql.*;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.Map;

public class SistemaNazionaleDAO extends BasicDAO {
    // Mappa di province Sigla -> Nome Es: CT -> Catania
   public LinkedHashMap<String,String>  getListProvince() {
       LinkedHashMap<String,String>  out = new LinkedHashMap<String,String>();
        String query = "SELECT sigla, nome FROM provincia ORDER BY sigla ASC";
        Connection conn = startConnection();
        Statement stmt = readyBasicStatement(conn);
        try {
           ResultSet results = stmt.executeQuery(query);
           while (results.next()) {
               String sigla = results.getString(1);
               String nome = results.getString(2);
               out.put(sigla,nome);
           }
           results.close();
           stmt.close();
           conn.close();
        }
        catch (SQLException ex) {
           System.err.println("Unable to retrieve provincia information" + ex.getMessage());
        }
        return out;
    }
// Codice fiscale / Nome Cognome

}
