package unitn.progweb.cocchiara.model;

import java.util.Date;

public class Prescrizione {
    Date datarilascio;
    String prestazione;
    String medico;

    String codiceMedico;

    Date dataevasione;
    String provincia;
    int idprescrizione;

    public Prescrizione(Date datarilascio, String prestazione, String medico, Date dataevasione, String provincia, int idprescrizione, String codiceMedico) {
        this.datarilascio = datarilascio;
        this.prestazione = prestazione;
        this.medico = medico;
        this.dataevasione = dataevasione;
        this.provincia = provincia;
        this.idprescrizione = idprescrizione;
        this.codiceMedico = codiceMedico;
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

    public String getCodiceMedico() {
        return codiceMedico;
    }

    public Date getDataevasione() {
        return dataevasione;
    }

    public int getIdprescrizione() {
        return idprescrizione;
    }

    public String getProvincia() { return provincia; }
}
