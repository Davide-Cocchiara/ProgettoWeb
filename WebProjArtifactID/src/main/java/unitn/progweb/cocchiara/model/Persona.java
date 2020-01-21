package unitn.progweb.cocchiara.model;


import java.util.Date;

public class Persona {
    String codicefiscale = "";
    String nome = "";
    String cognome ="";
    Date datanascita = null;
    String email ="";
    byte[] foto = null;
    String luogonascita = "";
    String provincia ="";
    String sesso = "";

    public Persona(String codicefiscale, String nome, String cognome, Date datanascita, String email, byte[] foto, String luogonascita, String provincia, String sesso) {
        this.codicefiscale = codicefiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.datanascita = datanascita;
        this.email = email;
        this.foto = foto;
        this.luogonascita = luogonascita;
        this.provincia = provincia;
        this.sesso = sesso;
    }

    public Persona() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDatanascita() {
        return datanascita;
    }

    public void setDatanascita(Date datanascita) {
        this.datanascita = datanascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getLuogonascita() {
        return luogonascita;
    }

    public void setLuogonascita(String luogonascita) {
        this.luogonascita = luogonascita;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public void setCodicefiscale(String codicefiscale) {
        this.codicefiscale = codicefiscale;
    }
}
