package unitn.progweb.cocchiara.model;

import java.util.Date;

public class Prescrizione {

    final static public int PRESTAZIONE_SPECIALISTICO = 0;
    final static public int PRESTAZIONE_FARMACO = 1;
    final static public int PRESTAZIONE_LABORATORIO = 2;


    Date datarilascio;
    String prestazione;
    String medico;

    String codiceMedico;

    Date dataevasione;
    String provincia;
    int idprescrizione;

    int tipo;


    double costo;

    public Prescrizione(Date datarilascio, String prestazione, String medico, Date dataevasione, String provincia, int idprescrizione, String codiceMedico, int tipo, double costo) {
        this.datarilascio = datarilascio;
        this.prestazione = prestazione;
        this.medico = medico;
        this.dataevasione = dataevasione;
        this.provincia = provincia;
        this.idprescrizione = idprescrizione;
        this.codiceMedico = codiceMedico;
        this.tipo = tipo;
        this.costo = costo;
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

    public int getTipo() {
        return tipo;
    }

    public double getCosto() {
        return costo;
    }

}
