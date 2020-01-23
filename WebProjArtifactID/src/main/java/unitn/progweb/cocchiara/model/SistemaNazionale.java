package unitn.progweb.cocchiara.model;

import unitn.progweb.cocchiara.dao.SistemaNazionaleDAO;

import java.util.LinkedHashMap;

public class SistemaNazionale {
    public LinkedHashMap<String, String> getListaProvince() {
        return listaProvince;
    }

    LinkedHashMap<String,String> listaProvince = null;

    public SistemaNazionale() {
        this.listaProvince = new SistemaNazionaleDAO().getListProvince();
        // To print every provincia in standard error.
      /*  for (Map.Entry<String,String> entry :listaProvince.entrySet()) {
            System.err.println(entry.getKey() +" "+ entry.getValue());
        }

       */
    }

}
