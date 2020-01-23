package unitn.progweb.cocchiara.model;

import unitn.progweb.cocchiara.dao.SistemaProvincialeDAO;

import java.util.LinkedHashMap;

public class SistemaProvinciale {
    public LinkedHashMap<String, String> getListaProvince() {
        return listaProvince;
    }

    LinkedHashMap<String,String> listaProvince = null;

    public SistemaProvinciale() {
        this.listaProvince = new SistemaProvincialeDAO().getListProvince();
        // To print every provincia in standard error.
      /*  for (Map.Entry<String,String> entry :listaProvince.entrySet()) {
            System.err.println(entry.getKey() +" "+ entry.getValue());
        }

       */
    }

    // Codice fiscale - Nome Cognome
    public LinkedHashMap<String, String> getListaMediciFromPronvincia(String provincia) {
        return new SistemaProvincialeDAO().getListaMediciFromPronvincia(provincia);

    }
}
