package unitn.progweb.cocchiara.model;


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


    public Boolean setEmail(String email) {
        // TODO
        return  false;
    }

    public Boolean setProvincia(String provincia) {
        // TODO
        return false;
    }
}
