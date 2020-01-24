package unitn.progweb.cocchiara.model;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unitn.progweb.cocchiara.dao.PazienteDAO;
import unitn.progweb.cocchiara.servlet.sispaz.RefertoSingolo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Paziente {
    String codicefiscale = "";
    String nome = "";
    String cognome ="";
    Date datanascita = null;
    String email ="";
    String luogonascita = "";
    String provincia ="";
    String sesso = "";
    String medicoAssegnato = "";

    public Paziente(String codicefiscale, String nome, String cognome, Date datanascita, String email, String luogonascita, String provincia, String sesso, String medicoAssegnato) {
        this.codicefiscale = codicefiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.datanascita = datanascita;
        this.email = email;
        this.luogonascita = luogonascita;
        this.provincia = provincia;
        this.sesso = sesso;
        this.medicoAssegnato = medicoAssegnato;
    }

    public Paziente() {

    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Date getDatanascita() {
        return datanascita;
    }

    public String getEmail() {
        return email;
    }

    public String getLuogonascita() {
        return luogonascita;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getSesso() {
        return sesso;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public String getMedicoAssegnato() {
        return medicoAssegnato;
    }

    public Boolean setMedicoAssegnato(@Nullable String codiceMedico) {
        PazienteDAO pd = new PazienteDAO();
        if(pd.setMedicoAssegnato(codicefiscale,codiceMedico)) {
            this.medicoAssegnato = codiceMedico;
            return true;
        }
        else
            return false;
    }

    public Boolean setEmail(@NotNull String email) {
        PazienteDAO pd = new PazienteDAO();
        if(pd.setEmail(codicefiscale,email)) {
            this.email = email;
            return true;
        }
        else
            return false;
    }

    public Boolean setProvincia(@NotNull String provincia) {
        PazienteDAO pd = new PazienteDAO();
        if(pd.setProvincia(codicefiscale,provincia)){
            this.provincia = provincia;
            setMedicoAssegnato(null);
            return true;
        }
        else
            return false;
    }

    public ArrayList<Referto> getListaRefertiMinimale() {
        PazienteDAO pd = new PazienteDAO();
        return pd.getListaRefertiMinimale(codicefiscale);
    }
    public Referto getReferto(int idreferto, String codicefiscale) {
        PazienteDAO pd = new PazienteDAO();
        return pd.getReferto(idreferto, codicefiscale);
    }

    public ArrayList<Pagamento> getListPagamentiMinimale() {
        PazienteDAO pd = new PazienteDAO();
        return pd.getListaPagamentiMinimale(codicefiscale);
    }
    public Pagamento getPagamento(int idPagamento, String codicefiscale) {
        PazienteDAO pd = new PazienteDAO();
        return pd.getPagamento(idPagamento, codicefiscale);
    }

    public ArrayList<Prescrizione> getListaPrescrizioniMinimale() {
        PazienteDAO pd = new PazienteDAO();
        return pd.getListaPrescrizioniMinimale(codicefiscale);
    }
    public Prescrizione getPrescrizione(int idPrescrizione, String codicefiscale) {
        PazienteDAO pd = new PazienteDAO();
        return pd.getPrescrizione(idPrescrizione, codicefiscale);
    }
}
