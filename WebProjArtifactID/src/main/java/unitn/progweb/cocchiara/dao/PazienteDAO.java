package unitn.progweb.cocchiara.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Utente;

import javax.swing.*;
import java.sql.*;

public class PazienteDAO extends BasicDAO {


    public Paziente getUserFromCodice(String codiceFiscale) {
        String query = "SELECT persona.codicefiscale,nome,cognome,datanascita,email,luogonascita,provincia,sesso, medicoassegnato.medico FROM persona LEFT JOIN medicoassegnato ON persona.codicefiscale=medicoassegnato.paziente WHERE persona.codicefiscale=?;";
        Connection conn = startConnection();
        Paziente out = new Paziente();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codiceFiscale);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                String _codice = results.getString(1);
                String _nome = results.getString(2);
                String _cognome = results.getString(3);
                Date _datanascita = results.getObject(4, Date.class);
                String _mail = results.getString(5);
                String _luogonascita = results.getString(6);
                String _provincia = results.getString(7);
                String _sesso = results.getString(8);
                String _dottore = results.getString(9);
                out = new Paziente(_codice, _nome, _cognome, _datanascita, _mail, _luogonascita, _provincia, _sesso, _dottore);
            }
            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get user from Codice Fiscale " + ex.getMessage());
        }
        return out;
    }

    public Boolean setProvincia(@NotNull String codiceFiscale, @NotNull String provincia) {
        try {
            String query = "UPDATE persona SET provincia=? WHERE codicefiscale=?;";
            Connection conn = startConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt = conn.prepareStatement(query);
            stmt.setString(1, provincia);
            stmt.setString(2, codiceFiscale);

            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();

            if (result != 1) {
                System.err.println("Unable to change provincia for user, records changed were: " + result);
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Unable to change provincia for user: " + ex.getMessage());
            return false;
        }
    }

    public Boolean setEmail(@NotNull String codiceFiscale, @NotNull String email) {
        try {
            String query = "UPDATE persona SET email=? WHERE codicefiscale=?;";
            Connection conn = startConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, codiceFiscale);

            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();

            if (result != 1) {
                System.err.println("Unable to change email for user, records changed were: " + result);
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Unable to change email for user: " + ex.getMessage());
            return false;
        }
    }

    public Boolean setMedicoAssegnato(@NotNull String codiceFiscale, @Nullable String codiceMedico) {
        try {
            String query;
            PreparedStatement stmt;
            Connection conn = startConnection();

            if(codiceMedico == null) // Remove current doc
            {
                query = "DELETE FROM medicoassegnato WHERE paziente=?;";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, codiceFiscale);
            }
            else {
                query = "UPDATE medicoassegnato SET medico=? WHERE paziente=?;";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, codiceMedico);
                stmt.setString(2, codiceFiscale);
            }

            stmt.execute();
            stmt.close();
            conn.close();

            return true;
        } catch (SQLException ex) {
            System.err.println("Unable to change medico for user: " + ex.getMessage());
            return false;
        }
    }
}
