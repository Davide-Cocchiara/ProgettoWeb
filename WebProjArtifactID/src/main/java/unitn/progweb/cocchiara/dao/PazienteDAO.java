package unitn.progweb.cocchiara.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unitn.progweb.cocchiara.model.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                query = "INSERT INTO medicoassegnato(paziente,medico) VALUES(?,?) ON CONFLICT (paziente) DO UPDATE SET medico=?;";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, codiceFiscale);
                stmt.setString(2, codiceMedico);
                stmt.setString(3, codiceMedico);
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

    public ArrayList<Referto> getListaRefertiMinimale(@NotNull String codicefiscale) {

        String query = "SELECT data,  descrizione, CONCAT(cognome,' ', nome), visite.id FROM visite INNER JOIN prestazioni ON prestazione=prestazioni.id " +
                "INNER JOIN persona ON visite.medico = persona.codicefiscale WHERE visite.paziente=?;";

        Connection conn = startConnection();
        ArrayList<Referto> retVal = new ArrayList<Referto>();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codicefiscale);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Referto r = new Referto(
                        results.getDate(1),
                        results.getString(2),
                        results.getString(3),
                        "",
                results.getInt(4));

                retVal.add(r);
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get lista referti minimale " + ex.getMessage());
        }
        return retVal;
    }

    public Referto getReferto(@NotNull int idReferto, @Nullable String codicefiscale) {

        String query;
        Connection conn = startConnection();
        PreparedStatement stmt;



        Referto retVal = null;
        try {

            if(codicefiscale == null) {
                query = "SELECT DATA,descrizione,CONCAT(cognome,' ', nome) AS medico,relazione,visite.id" +
                        " FROM visite " +
                        " INNER JOIN medico ON medico.codicefiscale=medico " +
                        " INNER JOIN prestazioni ON prestazione=prestazioni.id " +
                        " INNER JOIN persona on medico.codicefiscale=persona.codicefiscale " +
                        " where visite.id=?;";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, idReferto);
            }
            else
            {
                query = "SELECT DATA,descrizione,CONCAT(cognome,' ', nome) AS medico,relazione,visite.id" +
                        " FROM visite " +
                        " INNER JOIN medico ON medico.codicefiscale=medico " +
                        " INNER JOIN prestazioni ON prestazione=prestazioni.id " +
                        " INNER JOIN persona on medico.codicefiscale=persona.codicefiscale " +
                        " where visite.id=? AND visite.paziente=?;";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, idReferto);
                stmt.setString(2, codicefiscale);
            }

            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                retVal = new Referto(
                        results.getDate(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getInt(5));

            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get referto singolo " + ex.getMessage());
        }
        return retVal;
    }



    public ArrayList<Prescrizione> getListaPrescrizioniMinimale(@NotNull String codicefiscale) {

        String query = "SELECT datarilascio, descrizione, CONCAT(cognome,' ', nome) AS medico, dataevasione, prescrizioni.provinciarilascio, prescrizioni.id " +
                "FROM prescrizioni " +
                "INNER JOIN prestazioni ON prestazione=prestazioni.id " +
                "INNER JOIN medico ON medico.codicefiscale=medico " +
                "INNER JOIN persona on medico.codicefiscale=persona.codicefiscale " +
                "WHERE prescrizioni.paziente=?;";

        Connection conn = startConnection();
        ArrayList<Prescrizione> retVal = new ArrayList<Prescrizione>();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codicefiscale);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Prescrizione r = new Prescrizione(
                        results.getDate(1),
                        results.getString(2),
                        results.getString(3),
                        results.getDate(4),
                        results.getString(5),
                        results.getInt(6));

                retVal.add(r);
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get lista referti minimale " + ex.getMessage());
        }
        return retVal;
    }


    public Prescrizione getPrescrizione(@NotNull int idPrescrizione, @Nullable String codicefiscale) {

        String query;
        Connection conn = startConnection();
        PreparedStatement stmt;



        Prescrizione retVal = null;
        try {

            if(codicefiscale == null) {
                query = "SELECT datarilascio, descrizione, CONCAT(cognome,' ', persona.nome) AS medico, dataevasione, provincia.nome, prescrizioni.id " +
                        "FROM prescrizioni " +
                        "INNER JOIN prestazioni ON prestazione=prestazioni.id " +
                        "INNER JOIN medico ON medico.codicefiscale=medico " +
                        "INNER JOIN persona on medico.codicefiscale=persona.codicefiscale " +
                        "INNER JOIN provincia ON provincia.sigla=provinciarilascio " +
                        "WHERE prescrizioni.id=?;";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, idPrescrizione);
            }
            else
            {
                query = "SELECT datarilascio, descrizione, CONCAT(cognome,' ', persona.nome) AS medico, dataevasione, provincia.nome, prescrizioni.id " +
                        "FROM prescrizioni " +
                        "INNER JOIN prestazioni ON prestazione=prestazioni.id " +
                        "INNER JOIN medico ON medico.codicefiscale=medico " +
                        "INNER JOIN persona on medico.codicefiscale=persona.codicefiscale " +
                        "INNER JOIN provincia ON provincia.sigla=provinciarilascio " +
                        "WHERE prescrizioni.id=? AND prescrizioni.paziente=?;";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, idPrescrizione);
                stmt.setString(2, codicefiscale);

            }


            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                retVal = new Prescrizione(
                        results.getDate(1),
                        results.getString(2),
                        results.getString(3),
                        results.getDate(4),
                        results.getString(5),
                        results.getInt(6));
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get prescrizione singola " + ex.getMessage());
        }
        return retVal;
    }

    public ArrayList<Pagamento> getListaPagamentiMinimale(@NotNull String codicefiscale) {

        String query = "SELECT emesso, prestazioni.descrizione, ssp_prestazionidisponibili.costo, pagato, nome, ticket.id " +
                "FROM ticket INNER JOIN ssp_prestazionidisponibili ON ssp_prestazionidisponibili.idprovincia=ticket.idprovincia " +
                "AND ssp_prestazionidisponibili.idprestazione=ticket.idprestazione " +
                "INNER JOIN prestazioni ON prestazioni.id=ticket.idprestazione " +
                "INNER JOIN provincia ON provincia.sigla=ticket.idprovincia " +
                "WHERE ticket.paziente=?;";

        Connection conn = startConnection();
        ArrayList<Pagamento> retVal = new ArrayList<Pagamento>();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codicefiscale);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Pagamento r = new Pagamento(
                        results.getDate(1),
                        results.getString(2),
                        results.getString(3),
                        results.getDate(4),
                        results.getString(5),
                        results.getInt(6));

                retVal.add(r);
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get lista pagamenti minimale " + ex.getMessage());
        }
        return retVal;
    }


    public Pagamento getPagamento(@NotNull int idPagamento, @Nullable String codicefiscale) {

        Connection conn = startConnection();
        PreparedStatement stmt;
        String query;


        Pagamento retVal = null;
        try {

            if(codicefiscale == null)
            {
                query = "SELECT emesso, prestazioni.descrizione, ssp_prestazionidisponibili.costo, pagato, nome, ticket.id " +
                        "FROM ticket INNER JOIN ssp_prestazionidisponibili ON ssp_prestazionidisponibili.idprovincia=ticket.idprovincia " +
                        "AND ssp_prestazionidisponibili.idprestazione=ticket.idprestazione " +
                        "INNER JOIN prestazioni ON prestazioni.id=ticket.idprestazione " +
                        "INNER JOIN provincia ON provincia.sigla=ticket.idprovincia " +
                        "WHERE ticket.id=?;";
                stmt = conn.prepareStatement(query);

                stmt.setInt(1, idPagamento);
            }
            else
            {
                query = "SELECT emesso, prestazioni.descrizione, ssp_prestazionidisponibili.costo, pagato, nome, ticket.id " +
                        "FROM ticket INNER JOIN ssp_prestazionidisponibili ON ssp_prestazionidisponibili.idprovincia=ticket.idprovincia " +
                        "AND ssp_prestazionidisponibili.idprestazione=ticket.idprestazione " +
                        "INNER JOIN prestazioni ON prestazioni.id=ticket.idprestazione " +
                        "INNER JOIN provincia ON provincia.sigla=ticket.idprovincia " +
                        "WHERE ticket.id=? AND ticket.paziente=?;";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, idPagamento);
                stmt.setString(2, codicefiscale);
            }

            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                retVal = new Pagamento(
                        results.getDate(1),
                        results.getString(2),
                        results.getString(3),
                        results.getDate(4),
                        results.getString(5),
                        results.getInt(6));
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Unable to get pagamento singolo " + ex.getMessage());
        }
        return retVal;
    }

    public Boolean pagaPagamento(int idPagamento, java.sql.Date data, String codicefiscale) {
        try {
            String query = "UPDATE ticket SET pagato=? WHERE id=? AND paziente=? AND pagato IS NULL";
            Connection conn = startConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt = conn.prepareStatement(query);
            stmt.setDate(1, data);
            stmt.setInt(2, idPagamento);
            stmt.setString(3,codicefiscale);

            int result = stmt.executeUpdate();
            stmt.close();
            conn.close();

            if (result != 1) {
                System.err.println("Unable to pay for given pagamento "+ idPagamento + ", rows returned: " + result);
                System.err.println(idPagamento);
                System.err.println(data);
                System.err.println(codicefiscale);
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Unable to  pay for given pagamento: "+ idPagamento +",  "+ ex.getMessage());
            return false;
        }
    }
}