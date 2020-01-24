package unitn.progweb.cocchiara.model;

import java.util.Date;

public class Prescrizione {
    Date datarilascio;
    String prestazione;
    String medico;
    Date dataevasione;
    int id;

    public Prescrizione(Date datarilascio, String prestazione, String medico, Date dataevasione, int id) {
        this.datarilascio = datarilascio;
        this.prestazione = prestazione;
        this.medico = medico;
        this.dataevasione = dataevasione;
        this.id = id;
    }
}
