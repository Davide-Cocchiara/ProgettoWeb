package unitn.progweb.cocchiara.dao;

import unitn.progweb.cocchiara.model.Admin;
import unitn.progweb.cocchiara.model.Medico;
import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Utente;

import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO extends BasicDAO {






    public Utente getUserCred(String username, String password) {
        String query = "SELECT persona.codicefiscale FROM persona JOIN account ON persona.codicefiscale=account.codicefiscale WHERE persona.codicefiscale=? AND hashpass=crypt(?, hashpass);";
        Connection conn = startConnection();
        Utente out = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet results = stmt.executeQuery();
            if (results.next())
            {
                Paziente paziente = new PazienteDAO().getUserCodice(results.getString(1));
                Medico medico = new MedicoDAO().getUserCodice(results.getString(1));
                Admin admin = new AdminDAO().getUserCodice(results.getString(1));
                out = new Utente(paziente,medico,admin);
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to search for the Utente: " + ex.getMessage());
        }
        return out;
    }

    public Utente getUserCodice(String codiceFiscale) {
        Paziente paziente = new PazienteDAO().getUserCodice(codiceFiscale);
        Medico medico = new MedicoDAO().getUserCodice(codiceFiscale);
        Admin admin = new AdminDAO().getUserCodice(codiceFiscale);
        return new Utente(paziente,medico,admin);
    }

    public Boolean changePassword(String username, String oldpass, String newpass)
    {
        String query = "SELECT codicefiscale from account WHERE codicefiscale=? AND hashpass=crypt(?, hashpass);";
        Connection conn = startConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, oldpass);
            ResultSet results = stmt.executeQuery();
            if (!results.next()) // Wrong password
            {
                results.close();
                stmt.close();
                conn.close();
                return false;
            }

            results.close();
            stmt.close();

            query = "UPDATE account SET hashpass=crypt(?, hashpass) WHERE codicefiscale=?";

            stmt = conn.prepareStatement(query);
            stmt.setString(1, newpass);
            stmt.setString(2, username);

            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();

            if(result != 1) {
                System.err.println("Unable to change password for user, records changed were: " + result);
                return false;
            }
            return true;
        }
        catch (SQLException ex) {
            System.err.println("Unable to check password for user: " + ex.getMessage());
            return false;
        }

    }
}
