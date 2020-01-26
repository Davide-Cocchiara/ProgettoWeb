package unitn.progweb.cocchiara.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unitn.progweb.cocchiara.model.Medico;
import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Utente;

import java.sql.*;
import java.util.AbstractMap;
import java.util.Map;
import java.util.UUID;

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
                Paziente paziente = new PazienteDAO().getUserFromCodice(results.getString(1));
                Medico medico = new MedicoDAO().getMedicoFromCodice(results.getString(1));
                Boolean isAdmin = new AdminDAO().isAdmin(results.getString(1),conn);
                out = new Utente(paziente,medico,isAdmin);
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

    public Utente getUserFromCodice(String codiceFiscale) {
        Paziente paziente = new PazienteDAO().getUserFromCodice(codiceFiscale);
        Medico medico = new MedicoDAO().getMedicoFromCodice(codiceFiscale);
        Boolean isAdmin = new AdminDAO().isAdmin(codiceFiscale, null);
        return new Utente(paziente,medico,isAdmin);
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

            query = "UPDATE account SET hashpass=crypt(?, gen_salt('bf', 8)) WHERE codicefiscale=?";

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

    @Nullable
    public Map.Entry<String,String> createOrUpdateCookieForUser(@NotNull String codiceFiscale)
    {
        String token = UUID.randomUUID().toString();

        Date expires = new Date(System.currentTimeMillis() + 7*24*60*60*1000);

        String query = "INSERT INTO authtoken(codicefiscale,token,expires) VALUES (?,crypt(?, gen_salt('bf', 8)),?) " +
                "ON CONFLICT (codicefiscale) DO UPDATE SET token=crypt(?, gen_salt('bf', 8)), expires=? RETURNING id;";

        Connection conn = startConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codiceFiscale);
            stmt.setString(2, token);
            stmt.setDate(3,expires);
            stmt.setString(4,token);
            stmt.setDate(5,expires);
            ResultSet results = stmt.executeQuery();

            if (!results.next()) // Error!
            {
                results.close();
                stmt.close();
                conn.close();
                System.err.println("Unable to create/update cookie token for user: " + codiceFiscale);
                return null;
            }

            int resId = results.getInt(1);
            results.close();
            stmt.close();
            conn.close();

            Map.Entry<String,String> retVal=new AbstractMap.SimpleEntry<>(String.valueOf(resId),token);

            return retVal;
        }
        catch (SQLException ex) {
            System.err.println("Unable to create/update cookie token for user: " + ex.getMessage());
            return null;
        }

    }

    @Nullable // Return null if invalid, or codicefiscale if valid
    public String tryLoginWithCookie(@NotNull String authId, @NotNull String authToken)
    {

        String query = "SELECT codicefiscale from authtoken WHERE id=? AND token=crypt(?, token) AND expires>NOW();";

        Connection conn = startConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(authId));
            stmt.setString(2, authToken);

            ResultSet results = stmt.executeQuery();

            if (!results.next()) // Invalid cookie!
                return null;

            String retVal = results.getString(1);
            results.close();
            stmt.close();
            conn.close();

            return retVal;
        }
        catch (SQLException ex) {
            System.err.println("Unable to try to login with cookie: " + ex.getMessage());
            return null;
        }
        catch (Exception e)
        {
            System.err.println("Error trying to login with cookie: " + e.getMessage());
            return null;
        }

    }

    @Nullable
    public void deleteCookieForUser(@NotNull String codiceFiscale)
    {
        String query = "DELETE FROM authtoken WHERE codicefiscale=?";

        Connection conn = startConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codiceFiscale);

            stmt.execute();
            stmt.close();
            conn.close();

        }
        catch (SQLException ex) {
            System.err.println("Unable to delete cookie for user: " + ex.getMessage());
        }
    }
}
