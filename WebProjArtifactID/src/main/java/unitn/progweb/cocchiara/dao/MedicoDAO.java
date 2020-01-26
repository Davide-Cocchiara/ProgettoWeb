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

    public LinkedHashMap<String,String> getListEsamiRefertabiliPaziente(String codicepaziente) {
        // In caso di visita di base, l'idricetta è -1
        // TODO bro
        return null;
    }

    public Boolean addReferto(java.sql.Date sqlDate, String idricetta, String relazione, String codicefiscalePaziente) {
        // In caso di visita di base, l'idricetta è -1
        // TODO bro
        return false;
    }

    public Boolean addRicettaEsame(String idesame, String codicefiscalePaziente) {
        // TODO bro
        return false;
    }

    public Boolean addRicettaFarmaco(String idfarmaco, String codicefiscalePaziente) {
        // TODO bro
        return false;
    }


}
