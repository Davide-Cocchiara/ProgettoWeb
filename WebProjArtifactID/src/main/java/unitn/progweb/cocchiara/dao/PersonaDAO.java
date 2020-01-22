package unitn.progweb.cocchiara.dao;

import unitn.progweb.cocchiara.model.Persona;

import java.sql.*;
import java.util.ArrayList;

public class PersonaDAO extends BasicDAO {

    /*


INSERT INTO account (username,hashpass,type)
VALUES ('Marco',crypt('PassOne', gen_salt('bf', 8)),'PT'),
('Paolo',crypt('PasswordPaolo', gen_salt('bf', 8)),'DC')


SELECT username,type FROM account WHERE username='Marco' AND hashpass=crypt('PassOne', hashpass)
     */

    /*public ArrayList<Persona> getUsers() {
        String query = "SELECT persona.codicefiscale,nome,cognome,datanascita,email,luogonascita,provincia,sesso, medicoassegnato.medico FROM persona LEFT JOIN medicoassegnato ON persona.codicefiscale=medicoassegnato.persona";
        Connection conn = startConnection();
        Statement stmt = readyBasicStatement(conn);
        ArrayList<Persona> out = new ArrayList<Persona>();
        try {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()) {
                String _codice = results.getString(1);
                String _nome = results.getString(2);
                String _cognome = results.getString(3);
                Date _datanascita = results.getObject(4, Date.class);
                String _mail = results.getString(5);
                String _luogonascita = results.getString(6);
                String _provincia = results.getString(7);
                String _sesso = results.getString(8);
                String _dottore = results.getString(9);
                out.add(new Persona(_codice,_nome,_cognome,_datanascita,_mail,_luogonascita,_provincia,_sesso,isMedico(_codice,conn),isAdmin(_codice,conn), _dottore));
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to get the users: " + ex.getMessage());
        }
        return out;
    }
     */

    private Boolean isMedico(String codicefiscale, Connection conn) {
        String query = "SELECT codicefiscale FROM medico WHERE codicefiscale=?";
        boolean out = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codicefiscale);
            ResultSet results = stmt.executeQuery();
            out = results.next();
            results.close();
            stmt.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to check if user is medico: " + ex.getMessage());
        }
        return out;
    }

    private Boolean isAdmin(String codicefiscale,Connection conn) {
        String query = "SELECT codicefiscale FROM admin WHERE codicefiscale=?";
        boolean out = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codicefiscale);
            ResultSet results = stmt.executeQuery();
            out = results.next();
            results.close();
            stmt.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to check if user is admin: " + ex.getMessage());
        }
        return out;
    }

    public Persona getUserCred(String username, String password) {
        String query = "SELECT persona.codicefiscale,nome,cognome,datanascita,email,luogonascita,provincia,sesso, medicoassegnato.medico FROM persona JOIN account ON persona.codicefiscale=account.codicefiscale LEFT JOIN medicoassegnato ON persona.codicefiscale=medicoassegnato.paziente WHERE persona.codicefiscale=? AND hashpass=crypt(?, hashpass);";
        Connection conn = startConnection();
        Persona out = new Persona();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet results = stmt.executeQuery();
            if (results.next())
            {
                String _codice = results.getString(1);
                String _nome = results.getString(2);
                String _cognome = results.getString(3);
                Date _datanascita = results.getObject(4, Date.class);
                String _mail = results.getString(5);
                String _luogonascita = results.getString(6);
                String _provincia = results.getString(7);
                String _sesso = results.getString(8);
                String _dottore = results.getString(9);
                out = new Persona(_codice,_nome,_cognome,_datanascita,_mail,_luogonascita,_provincia,_sesso,isMedico(_codice,conn),isAdmin(_codice,conn),_dottore);
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Unable to search for the user: " + ex.getMessage());
        }
        return out;
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
