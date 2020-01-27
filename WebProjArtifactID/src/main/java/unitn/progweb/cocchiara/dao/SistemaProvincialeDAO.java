package unitn.progweb.cocchiara.dao;

import unitn.progweb.cocchiara.model.Paziente;

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
        LinkedHashMap<String,String>  out = new LinkedHashMap<String,String>();
        String query = "SELECT idprestazione,descrizione FROM ssp_prestazionidisponibili " +
                "INNER JOIN prestazioni ON prestazioni.id=idprestazione " +
                "WHERE idprovincia=? AND tipo=1 " +
                "AND idprestazione !='-1'";

        Connection conn = startConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, provincia);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                String idPrestazione = String.valueOf(results.getInt(1));
                String desc = results.getString(2);

                out.put(idPrestazione,desc);
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to retrive farmaci from provincia" + ex.getMessage());
        }
        return out;

    }
    public LinkedHashMap<String,String> getListaEsamiFromProvincia(String provincia) {
        LinkedHashMap<String,String>  out = new LinkedHashMap<String,String>();
        String query = "SELECT idprestazione,descrizione FROM ssp_prestazionidisponibili " +
                "INNER JOIN prestazioni ON prestazioni.id=idprestazione " +
                "WHERE idprovincia=? AND (tipo=0 OR tipo=2) " +
                "AND idprestazione !='-1'";

        Connection conn = startConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, provincia);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                String idPrestazione = String.valueOf(results.getInt(1));
                String desc = results.getString(2);

                out.put(idPrestazione,desc);
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to retrive esami from provincia" + ex.getMessage());
        }
        return out;

    }

    public ArrayList<Paziente> getListaPazientiMinimaleFromProvincia(String provincia) {

        String query = "SELECT codicefiscale, nome, cognome, datanascita, medico FROM persona " +
                "INNER JOIN medicoassegnato ON medicoassegnato.paziente=codicefiscale " +
                "WHERE provincia=?;";

        Connection conn = startConnection();
        ArrayList<Paziente> retVal = new ArrayList<Paziente>();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, provincia);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Paziente p = new Paziente(
                        results.getString(1),
                        results.getString(2),
                        results.getString(3),
                        results.getDate(4),
                        "",
                        "",
                        provincia,
                        "",
                        results.getString(5));

                retVal.add(p);
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get lista pazienti minimale " + ex.getMessage());
        }
        return retVal;
    }
}
