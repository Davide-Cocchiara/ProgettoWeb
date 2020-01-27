package unitn.progweb.cocchiara.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unitn.progweb.cocchiara.model.Medico;
import unitn.progweb.cocchiara.model.Paziente;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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

                out = new Medico(_indirizzo, _telefono);
            }
            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get medico from Codice Fiscale " + ex.getMessage());
        }
        return out;
    }


    public boolean setInfoClinica(@NotNull String codiceFiscale, @NotNull String indirizzo, @NotNull String numero) {

        try {
            String query = "UPDATE medico SET indirizzoclinica=?, telefonoclinica=? WHERE codicefiscale=?;";
            Connection conn = startConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt = conn.prepareStatement(query);
            stmt.setString(1, indirizzo);
            stmt.setString(2, numero);
            stmt.setString(3, codiceFiscale);

            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();

            if (result != 1) {
                System.err.println("Unable to change clinica for user, records changed were: " + result);
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Unable to change clinica for user: " + ex.getMessage());
            return false;
        }

    }

    public ArrayList<Paziente> getListaPazientiMinimale(String codiceMedico) {

        String query = "SELECT codicefiscale, nome, cognome, datanascita FROM persona " +
                "WHERE codicefiscale IN (SELECT paziente FROM medicoassegnato WHERE medico=?);";

        Connection conn = startConnection();
        ArrayList<Paziente> retVal = new ArrayList<Paziente>();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codiceMedico);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Paziente p = new Paziente(
                        results.getString(1),
                        results.getString(2),
                        results.getString(3),
                        results.getDate(4),
                        "",
                        "",
                        "",
                        "",
                        codiceMedico);

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

    public Boolean addReferto(@NotNull String codiceMedico, @NotNull String idricetta, @NotNull String relazione, @NotNull String codicefiscalePaziente,  @NotNull Boolean isPagato) {

        // Evasione ricetta specifica
        if (!idricetta.equals("-1")) {
            String query = "WITH prescriz AS (UPDATE prescrizioni SET dataevasione=NOW() WHERE id=? AND dataevasione IS NULL returning prestazione, provinciarilascio), " +
                    "insertval AS (INSERT INTO visite (paziente, medico, DATA, relazione, prestazione) " +
                    "SELECT ?, ?, NOW(), ?, prestazione " +
                    "FROM prescriz " +
                    "returning paziente,prestazione,DATA,NULL) " +
                    "INSERT INTO ticket(paziente,idprovincia,idprestazione,emesso,pagato) " +
                    "(SELECT paziente,provinciarilascio,insertval.prestazione,DATA, ? FROM insertval, prescriz);";


            try {
                Connection conn = startConnection();

                PreparedStatement stmt = conn.prepareStatement(query);

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(idricetta));
                stmt.setString(2, codicefiscalePaziente);
                stmt.setString(3, codiceMedico);
                stmt.setString(4, relazione);
                stmt.setDate(5, (isPagato ? new Date(System.currentTimeMillis()) : null));

                int result = stmt.executeUpdate();
                stmt.close();
                conn.close();

                if (result <= 0) {
                    System.err.println("No referto was added: " + result);
                    return false;
                }
                return true;
            } catch (SQLException ex) {
                System.err.println("Unable to add referto: " + ex.getMessage());
                return false;
            }
        } else // -1, visita di base
        {
            String query = "WITH visita as (INSERT INTO visite (paziente, medico, DATA, relazione, prestazione) " +
                    "VALUES(?, ?, NOW(), ?, -1) returning prestazione, paziente, data) " +
                    "INSERT INTO ticket(paziente,idprovincia,idprestazione,emesso,pagato) " +
                    "(SELECT ?,persona.provincia,visita.prestazione, DATA, ? FROM visita INNER JOIN persona ON persona.codicefiscale=visita.paziente);";

            try {
                Connection conn = startConnection();

                PreparedStatement stmt = conn.prepareStatement(query);

                stmt = conn.prepareStatement(query);
                stmt.setString(1, codicefiscalePaziente);
                stmt.setString(2, codiceMedico);
                stmt.setString(3, relazione);
                stmt.setString(4, codicefiscalePaziente);
                stmt.setDate(5, (isPagato ? new Date(System.currentTimeMillis()) : null));

                int result = stmt.executeUpdate();
                stmt.close();
                conn.close();

                if (result <= 0) {
                    System.err.println("No referto was added: " + result);
                    return false;
                }
                return true;
            } catch (SQLException ex) {
                System.err.println("Unable to add referto: " + ex.getMessage());
                return false;
            }
        }
    }

    public LinkedHashMap<String, Map.Entry<String,String>> getListEsamiRefertabiliPaziente(String codicepaziente, String provincia) {
        // In caso di visita di base, l'idricetta è -1

        String query = "WITH subq AS (SELECT idprestazione,costo FROM ssp_prestazionidisponibili  WHERE idprovincia=?) " +
                "SELECT prescrizioni.id, descrizione,costo FROM prescrizioni " +
                "INNER JOIN prestazioni " +
                "ON prescrizioni.prestazione=prestazioni.id " +
                "INNER JOIN subq ON prescrizioni.prestazione=subq.idprestazione " +
                "WHERE prestazioni.tipo=0 AND " +
                "prescrizioni.paziente=? AND " +
                "prescrizioni.dataevasione IS NULL " +
                "UNION " +
                "SELECT -1 AS id,descrizione,costo FROM prestazioni " +
                "INNER JOIN subq ON prestazioni.id=subq.idprestazione WHERE prestazioni.id=-1"; // Visita Base always available

        Connection conn = startConnection();
        LinkedHashMap<String, Map.Entry<String,String>> retVal = new LinkedHashMap<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,provincia);
            stmt.setString(2, codicepaziente);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Map.Entry<String,String> val = new AbstractMap.SimpleEntry<String,String>(results.getString(2),results.getString(3));

                retVal.put(String.valueOf(results.getInt(1)), val);
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get lista esami refertabili paziente " + ex.getMessage());
        }

        return retVal;

    }

    public Boolean addRicetta(@NotNull String codiceMedico, @NotNull String provinciaRilascio, @NotNull String idesame,@NotNull  String codicefiscalePaziente) {
        try {
            String query = "INSERT INTO prescrizioni(paziente,medico,provinciarilascio,prestazione,datarilascio) " +
                    "VALUES (?,?,?,?,NOW());";

            Connection conn = startConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt = conn.prepareStatement(query);
            stmt.setString(1, codicefiscalePaziente);
            stmt.setString(2, codiceMedico);
            stmt.setString(3, provinciaRilascio);
            stmt.setInt(4, Integer.parseInt(idesame));

            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();

            if (result != 1) {
                System.err.println("Unable to add ricetta for user, records changed were: " + result);
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Unable to add ricetta for user: " + ex.getMessage());
            return false;
        }

    }

}
