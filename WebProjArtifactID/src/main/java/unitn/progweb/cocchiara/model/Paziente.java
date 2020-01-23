package unitn.progweb.cocchiara.model;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unitn.progweb.cocchiara.dao.PazienteDAO;

import java.util.Date;

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
}
