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

    public ArrayList<Persona> getUsers() {
        String query = "SELECT codicefiscale,nome,cognome,datanascita,email,luogonascita,provincia,sesso FROM persona";
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
                out.add(new Persona(_codice,_nome,_cognome,_datanascita,_mail,_luogonascita,_provincia,_sesso,isMedico(_codice,conn),isAdmin(_codice,conn)));
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Impossible to get the users: " + ex.getMessage());
        }
        return out;
    }
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
            System.err.println("Impossible to check if user is medico: " + ex.getMessage());
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
            System.err.println("Impossible to check if user is admin: " + ex.getMessage());
        }
        return out;
    }

    public Persona getUserCred(String username, String password) {
        String query = "SELECT persona.codicefiscale,nome,cognome,datanascita,email,luogonascita,provincia,sesso FROM persona JOIN account ON persona.codicefiscale=account.codicefiscale WHERE persona.codicefiscale=? AND hashpass=crypt(?, hashpass);";
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
                out = new Persona(_codice,_nome,_cognome,_datanascita,_mail,_luogonascita,_provincia,_sesso,isMedico(_codice,conn),isAdmin(_codice,conn));
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Impossible to search for the user: " + ex.getMessage());
        }
        return out;
    }
}
