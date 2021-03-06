package unitn.progweb.cocchiara.dao;

import org.jetbrains.annotations.NotNull;
import unitn.progweb.cocchiara.model.Pagamento;
import unitn.progweb.cocchiara.model.Paziente;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class SistemaProvincialeDAO extends BasicDAO {
    public LinkedHashMap<String, String> getListaMediciFromPronvincia(String provincia) {
        LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
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

                out.put(codicefiscale, cognome + " " + nome);
            }
            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to retrive list medici of given provincia" + ex.getMessage());
        }
        return out;
    }

    public ArrayList<Map.Entry<String, Map.Entry<String, String>>> getListaPrestazioniFromProvincia(String provincia, int tipoPrestazione) {
        ArrayList<Map.Entry<String, Map.Entry<String, String>>> out = new ArrayList<Map.Entry<String, Map.Entry<String, String>>>();
        String query = "SELECT idprestazione, descrizione, costo FROM ssp_prestazionidisponibili " +
                "INNER JOIN prestazioni ON prestazioni.id=idprestazione " +
                "WHERE idprovincia=? AND tipo=? " +
                "AND idprestazione !='-1'" +
                "ORDER BY tipo,descrizione";

        Connection conn = startConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, provincia);
            stmt.setInt(2, tipoPrestazione );
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                String idPrestazione = String.valueOf(results.getInt(1));
                String desc = results.getString(2);
                String cost = results.getString(3);
                out.add(new AbstractMap.SimpleEntry<>(idPrestazione, new AbstractMap.SimpleEntry<>(desc,cost)));
            }
            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
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
        String query = "SELECT emesso, ticket.idprestazione, descrizione, costo, pagato, ticket.id, paziente " +
                "FROM ticket INNER JOIN prestazioni " +
                "ON ticket.idprestazione=prestazioni.id AND (tipo=?) " +
                "INNER JOIN ssp_prestazionidisponibili ON prestazioni.id=ssp_prestazionidisponibili.idprestazione " +
                "where pagato is not NULL " +
                "AND ssp_prestazionidisponibili.idprovincia=?;";

        Connection conn = startConnection();
        ArrayList<Map.Entry<String, Pagamento>> retVal = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, tipo);
            stmt.setString(2, provincia);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                // (Date dataemissione, String prestazione, String costo, Date datapagamento, String provinciarilascio, int idpagamento)
                Pagamento r = new Pagamento(
                        results.getDate(1),
                        "[" + results.getString(2) + "] " + results.getString(3),
                        results.getString(4),
                        results.getDate(5),
                        provincia,
                        results.getInt(6));

                retVal.add(new AbstractMap.SimpleEntry<>(results.getString(7), r));
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get lista prestazioni erogate provincia  " + ex.getMessage());
        }
        return retVal;
    }

    public Boolean erogaFarmaco(String provincia, Integer idprescrizione, Boolean pagato) {

        // Evasione ricetta specifica
        String query = "WITH prescriz AS (UPDATE prescrizioni SET dataevasione=NOW() WHERE id=? AND dataevasione IS NULL AND provinciarilascio=? " +
                "returning prestazione, provinciarilascio, paziente, datarilascio, dataevasione) " +
                "INSERT INTO ticket(paziente,idprovincia,idprestazione,emesso,pagato) " +
                "(SELECT paziente,provinciarilascio,prestazione,datarilascio,? FROM prescriz);";


        try {
            Connection conn = startConnection();

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idprescrizione);
            stmt.setString(2, provincia);
            stmt.setDate(3, (pagato ? new Date(System.currentTimeMillis()) : null));

            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();

            if (result <= 0) {
                System.err.println("No ricettaErogata was added: " + result);
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Unable to eroga Ricetta: " + ex.getMessage());
            return false;
        }

    }


    public Boolean erogaEsameLaboratorio(String provincia, String medico, String relazione, Integer idprescrizione) {

        // Evasione ricetta specifica
        String query = "WITH prescriz AS (UPDATE prescrizioni SET dataevasione=NOW() WHERE id=? AND dataevasione IS NULL returning prestazione, provinciarilascio, paziente), " +
                    "insertval AS (INSERT INTO visite (paziente, medico, DATA, relazione, prestazione) " +
                    "SELECT paziente, ?, NOW(), ?, prestazione " +
                    "FROM prescriz " +
                    "returning paziente,prestazione,DATA,NULL) " +
                    "INSERT INTO ticket(paziente,idprovincia,idprestazione,emesso) " +
                    "(SELECT prescriz.paziente,?,insertval.prestazione,DATA FROM insertval, prescriz);";


            try {
                Connection conn = startConnection();

                PreparedStatement stmt = conn.prepareStatement(query);

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, idprescrizione);
                stmt.setString(2, medico);
                stmt.setString(3, relazione);
                stmt.setString(4, provincia);

                int result = stmt.executeUpdate();
                stmt.close();
                conn.close();

                if (result <= 0) {
                    System.err.println("No referto lab was added: " + result);
                    return false;
                }
                return true;
            } catch (SQLException ex) {
                System.err.println("Unable to add referto lab: " + ex.getMessage());
                return false;
            }

    }
}
