package unitn.progweb.cocchiara.model;


import java.util.Date;

public class Pagamento {

    //dataemissione, prestazione, costo, datapagamento, provinciarilascio, id
    Date dataemissione;
    String prestazione;
    String costo;
    Date datapagamento;
    String provinciarilascio;
    int idPagamento;

    public Pagamento(Date dataemissione, String prestazione, String costo, Date datapagamento, String provinciarilascio, int idPagamento) {
        this.dataemissione = dataemissione;
        this.prestazione = prestazione;
        this.costo = costo;
        this.datapagamento = datapagamento;
        this.provinciarilascio = provinciarilascio;
        this.idPagamento = idPagamento;
    }

    public Date getDataemissione() {
        return dataemissione;
    }

    public String getPrestazione() {
        return prestazione;
    }

    public String getCosto() {
        return costo;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public String getProvinciarilascio() {
        return provinciarilascio;
    }

    public int getIdPagamento() {
        return idPagamento;
    }
}
