package unitn.progweb.cocchiara.model;


import java.util.Date;

public class Persona {
    String CodiceFiscale = "";
    String Nome = "";
    String Cognome ="";
    Date DataNascita = null;
    String Email ="";
    Byte[] Foto = null;
    String LuogoNascita = "";
    String Provincia ="";
    String Sesso = "";

    public Persona(String codiceFiscale, String nome, String cognome, Date dataNascita, String email, Byte[] foto, String luogoNascita, String provincia, String sesso) {
        CodiceFiscale = codiceFiscale;
        Nome = nome;
        Cognome = cognome;
        DataNascita = dataNascita;
        Email = email;
        Foto = foto;
        LuogoNascita = luogoNascita;
        Provincia = provincia;
        Sesso = sesso;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public Date getDataNascita() {
        return DataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        DataNascita = dataNascita;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Byte[] getFoto() {
        return Foto;
    }

    public void setFoto(Byte[] foto) {
        Foto = foto;
    }

    public String getLuogoNascita() {
        return LuogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        LuogoNascita = luogoNascita;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getSesso() {
        return Sesso;
    }

    public void setSesso(String sesso) {
        Sesso = sesso;
    }

    public String getCodiceFiscale() {
        return CodiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        CodiceFiscale = codiceFiscale;
    }
}
