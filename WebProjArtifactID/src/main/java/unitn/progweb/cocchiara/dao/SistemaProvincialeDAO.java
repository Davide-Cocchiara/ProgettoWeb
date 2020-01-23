package unitn.progweb.cocchiara.dao;


import java.sql.*;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.Map;

public class SistemaProvincialeDAO extends BasicDAO {
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
           System.err.println("Unable to retrive provincia information" + ex.getMessage());
        }
        return out;
    }
// Codice fiscale / Nome Cognome
    public LinkedHashMap<String, String> getListaMediciFromPronvincia(String provincia) {
        LinkedHashMap<String,String>  out = new LinkedHashMap<String,String>();
        String query = "SELECT medico.codicefiscale,persona.nome,persona.cognome FROM medico JOIN persona on medico.codicefiscale=persona.codicefiscale WHERE provincia=?;";
        Connection conn = startConnection();
        Statement stmt = readyBasicStatement(conn);
        try {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()) {
                String codicefiscale = results.getString(1);
                String nome = results.getString(2);
                String cognome = results.getString(3);

                out.put(codicefiscale,nome+ " " + cognome);
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to retrive list medici from provincia information" + ex.getMessage());
        }
        return out;
    }
}
