package unitn.progweb.cocchiara.model;

import java.util.Date;

public class Referto
{
    Date data;
    String prestazione;
    String medico;
    String relazione;
    int idreferto;

    public Referto(Date data, String prestazione, String medico, String relazione, int ID) {
        this.data = data;
        this.prestazione = prestazione;
        this.medico = medico;
        this.relazione = relazione;
        this.idreferto = ID;
    }

    public Date getData() {
        return data;
    }

    public String getPrestazione() {
        return prestazione;
    }

    public String getMedico() {
        return medico;
    }

    public String getRelazione() {
        return relazione;
    }

    public int getIdreferto() {
        return idreferto;
    }
}