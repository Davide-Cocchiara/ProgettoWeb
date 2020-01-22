package unitn.progweb.cocchiara.model;


import java.util.Date;

public class Persona {
    String codicefiscale = "";
    String nome = "";
    String cognome ="";
    Date datanascita = null;
    String email ="";
    String luogonascita = "";
    String provincia ="";
    String sesso = "";
    boolean medico = false;
    boolean admin = false;

    public Persona(String codicefiscale, String nome, String cognome, Date datanascita, String email,String luogonascita, String provincia, String sesso, boolean medico, boolean admin) {
        this.codicefiscale = codicefiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.datanascita = datanascita;
        this.email = email;
        this.luogonascita = luogonascita;
        this.provincia = provincia;
        this.sesso = sesso;
        this.medico = medico;
        this.admin = admin;
    }

    public Persona() {

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

    public boolean isMedico() {
        return medico;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean changePassword(String password, String newpassword) {
        return false;
    }
}
