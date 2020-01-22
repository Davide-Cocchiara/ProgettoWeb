package unitn.progweb.cocchiara.model;


import org.jetbrains.annotations.NotNull;
import unitn.progweb.cocchiara.dao.PersonaDAO;

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
    String medicoAssegnato = "";
    boolean bIsMedico = false;
    boolean admin = false;

    public Persona(String codicefiscale, String nome, String cognome, Date datanascita, String email, String luogonascita, String provincia, String sesso, boolean bIsMedico, boolean admin, String medicoAssegnato) {
        this.codicefiscale = codicefiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.datanascita = datanascita;
        this.email = email;
        this.luogonascita = luogonascita;
        this.provincia = provincia;
        this.sesso = sesso;
        this.bIsMedico = bIsMedico;
        this.admin = admin;
        this.medicoAssegnato = medicoAssegnato;
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
        return bIsMedico;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getMedicoAssegnato() {
        return medicoAssegnato;
    }


    public boolean changePassword(@NotNull String password, @NotNull String newpassword) {
        if(newpassword.length() < 8)
            return false;

        PersonaDAO pd = new PersonaDAO();
        return pd.changePassword(codicefiscale,password,newpassword);
    }
}
