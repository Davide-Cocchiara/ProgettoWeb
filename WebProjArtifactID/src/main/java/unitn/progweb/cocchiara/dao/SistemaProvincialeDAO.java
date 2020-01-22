package unitn.progweb.cocchiara.dao;

import unitn.progweb.cocchiara.model.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SistemaProvincialeDAO extends BasicDAO {
    // Mappa di province Sigla -> Nome Es: CT -> Catania
   public Map<String,String>  getListProvince() {
        Map<String,String>  out = new HashMap<String,String>();
        String query = "SELECT sigla, nome FROM provincia";
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
           System.err.println("Unable to retrive provincia information" + ex.getMessage());
        }
        return out;
    }
}
