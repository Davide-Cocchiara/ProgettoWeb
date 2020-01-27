package unitn.progweb.cocchiara.dao;

import unitn.progweb.cocchiara.model.Pagamento;
import unitn.progweb.cocchiara.model.Paziente;

import java.sql.*;
import java.util.*;

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
    public LinkedHashMap<String,String> getListaEsamiFromProvincia(String provincia, Boolean diLaboratorio) {
        LinkedHashMap<String,String>  out = new LinkedHashMap<String,String>();
        String query = "SELECT idprestazione,descrizione FROM ssp_prestazionidisponibili " +
                "INNER JOIN prestazioni ON prestazioni.id=idprestazione " +
                "WHERE idprovincia=? AND tipo=? " +
                "AND idprestazione !='-1'" +
                "ORDER BY tipo,descrizione";

        Connection conn = startConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, provincia);
            stmt.setInt(2,diLaboratorio ? 2 : 0);
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


    // Paziente, DatiPagamento
    public ArrayList<Map.Entry<String, Pagamento>> getReportPrestazioniErogateFromProvincia(String provincia, int tipo) {
        String query = "SELECT emesso, idprestazione, descrizione, costo, pagato, ticket.id, paziente " +
                "FROM ticket INNER JOIN prestazioni " +
                "ON ticket.idprestazione=prestazioni.id AND (tipo=?) " +
                "INNER JOIN ssp_prestazionidisponibili ON prestazioni.id=ssp_prestazionidisponibili.idprestazione " +
                "where pagato is not NULL " +
                "AND idprovincia=?;";

        Connection conn = startConnection();
        ArrayList<Map.Entry<String, Pagamento>> retVal = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, tipo);
            stmt.setString(2,provincia);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                // (Date dataemissione, String prestazione, String costo, Date datapagamento, String provinciarilascio, int idpagamento)
                Pagamento r = new Pagamento(
                        results.getDate(1),
                        "[" + results.getString(2) + "] " + results.getString(3),
                        results.getString(4),
                        results.getDate(5),
                        results.getString(6),
                        results.getInt(7));

                retVal.add(new AbstractMap.SimpleEntry<>(results.getString(8), r));
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get lista prestazioni erogate provincia  " + ex.getMessage());
        }
        return retVal;
    }

}
