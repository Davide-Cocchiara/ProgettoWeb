package unitn.progweb.cocchiara.model;

import unitn.progweb.cocchiara.dao.SistemaProvincialeDAO;

import java.util.LinkedHashMap;

public class SistemaProvinciale {
    String Provincia;
    public SistemaProvinciale(String provincia) {
        Provincia = provincia;
    }

    public String getProvincia() {
        return Provincia;
    }



    // Codice fiscale - Nome Cognome
    public LinkedHashMap<String, String> getListaMedici() {
        return new SistemaProvincialeDAO().getListaMediciFromPronvincia(Provincia);
    }
}
