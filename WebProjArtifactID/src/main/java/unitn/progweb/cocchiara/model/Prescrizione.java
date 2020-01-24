package unitn.progweb.cocchiara.model;

import java.util.Date;

public class Prescrizione {
    Date datarilascio;
    String prestazione;
    String medico;
    Date dataevasione;
    String provincia;
    int id;

    public Prescrizione(Date datarilascio, String prestazione, String medico, Date dataevasione, String provincia, int id) {
        this.datarilascio = datarilascio;
        this.prestazione = prestazione;
        this.medico = medico;
        this.dataevasione = dataevasione;
        this.provincia = provincia;
        this.id = id;
    }

    public Date getDatarilascio() {
        return datarilascio;
    }

    public String getPrestazione() {
        return prestazione;
    }

    public String getMedico() {
        return medico;
    }

    public Date getDataevasione() {
        return dataevasione;
    }

    public int getId() {
        return id;
    }

}
