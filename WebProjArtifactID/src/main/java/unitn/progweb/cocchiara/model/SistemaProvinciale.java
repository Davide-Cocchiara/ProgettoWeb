package unitn.progweb.cocchiara.model;

import unitn.progweb.cocchiara.dao.SistemaProvincialeDAO;

import java.util.LinkedHashMap;
import java.util.ArrayList;

public class SistemaProvinciale {
    String provincia;
    public SistemaProvinciale(String provincia) {
        this.provincia = provincia;
    }

    public String getProvincia() {
        return provincia;
    }



    // Codice fiscale - Nome Cognome
    public LinkedHashMap<String, String> getListaMedici() {
        return new SistemaProvincialeDAO().getListaMediciFromPronvincia(provincia);
    }
    public LinkedHashMap<String,String> getListaFarmaciDisponibili() {
        return new SistemaProvincialeDAO().getListaFarmaciFromProvincia(provincia);
    }
    public LinkedHashMap<String,String> getListaEsamiDisponibili() {
        return new SistemaProvincialeDAO().getListaEsamiFromProvincia(provincia);
    }
}
