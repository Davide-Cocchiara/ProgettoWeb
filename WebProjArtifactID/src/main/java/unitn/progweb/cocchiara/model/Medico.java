package unitn.progweb.cocchiara.model;

import java.util.ArrayList;
import java.util.Date;

public class Medico  {
    String clinica = "";
    String telefonoclinica = "";


    public Medico(String clinica, String telefonoclinica) {
        this.clinica = clinica;
        this.telefonoclinica = telefonoclinica;
    }

    public String getClinica() {
        return clinica;
    }

    public String getTelefonoclinica() {
        return telefonoclinica;
    }

    public boolean setClinica(String indirizzo) {
        // TODO bro
        return  false;
    }

    public boolean setTelefonoclinica(String telefono) {
        // TODO bro
        return false;
    }

    public ArrayList<Paziente> getListPazienti() {
        // TODO bro
        return null;
    }

    public Paziente getOwnpaziente(String s_selectedpaziente) {
        // TODO bro
        return null;
    }
}
