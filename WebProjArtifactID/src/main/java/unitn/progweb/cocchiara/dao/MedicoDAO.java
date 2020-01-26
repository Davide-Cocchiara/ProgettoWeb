package unitn.progweb.cocchiara.dao;

import org.jetbrains.annotations.NotNull;
import unitn.progweb.cocchiara.model.Medico;
import unitn.progweb.cocchiara.model.Paziente;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

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

    public Boolean addReferto(@NotNull String codiceMedico, @NotNull String idricetta, @NotNull String relazione, @NotNull String codicefiscalePaziente) {

        // Evasione ricetta specifica
        if (!idricetta.equals("-1")) {
            String query = "WITH prescriz AS (UPDATE prescrizioni SET dataevasione=NOW() WHERE id=? AND dataevasione IS NULL returning prestazione) " +
                    "INSERT INTO visite (paziente, medico, DATA, relazione, prestazione) " +
                    "SELECT ?, ?, NOW(), ?, prestazione " +
                    "FROM prescriz;";


            try {
                Connection conn = startConnection();

                PreparedStatement stmt = conn.prepareStatement(query);

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(idricetta));
                stmt.setString(2, codicefiscalePaziente);
                stmt.setString(3, codiceMedico);
                stmt.setString(4, relazione);

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
            String query = "INSERT INTO visite (paziente, medico, DATA, relazione, prestazione) " +
                    "VALUES(?, ?, NOW(), ?, -1);";

            try {
                Connection conn = startConnection();

                PreparedStatement stmt = conn.prepareStatement(query);

                stmt = conn.prepareStatement(query);
                stmt.setString(1, codicefiscalePaziente);
                stmt.setString(2, codiceMedico);
                stmt.setString(3, relazione);

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

    public LinkedHashMap<String, String> getListEsamiRefertabiliPaziente(String codicepaziente) {
        // In caso di visita di base, l'idricetta è -1

        String query = "SELECT prescrizioni.id, descrizione FROM prescrizioni " +
                "INNER JOIN prestazioni " +
                "ON prescrizioni.prestazione=prestazioni.id " +
                "WHERE prestazioni.tipo=0 AND " +
                "prescrizioni.paziente=? UNION " +
                "SELECT -1 AS id,descrizione FROM prestazioni WHERE prestazioni.id=-1;"; // Visita Base always available

        Connection conn = startConnection();
        LinkedHashMap<String, String> retVal = new LinkedHashMap<String, String>();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codicepaziente);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                retVal.put(String.valueOf(results.getInt(1)), results.getString(2));
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get lista esami refertabili paziente " + ex.getMessage());
        }

        return retVal;

    }

    public Boolean addRicettaEsame(@NotNull String codiceMedico, @NotNull String provinciaRilascio, @NotNull String idesame,@NotNull  String codicefiscalePaziente) {
        // TODO bro

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
                System.err.println("Unable to change clinica for user, records changed were: " + result);
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Unable to change clinica for user: " + ex.getMessage());
            return false;
        }

    }

    public Boolean addRicettaFarmaco(@NotNull String codiceMedico, @NotNull String provinciaRilascio, @NotNull String idfarmaco,@NotNull String codicefiscalePaziente) {
        // TODO bro
        return false;
    }
}
