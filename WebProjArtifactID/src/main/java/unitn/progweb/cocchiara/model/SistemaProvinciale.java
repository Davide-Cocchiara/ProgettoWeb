package unitn.progweb.cocchiara.model;

import unitn.progweb.cocchiara.dao.SistemaProvincialeDAO;

import java.util.Map;

public class SistemaProvinciale {
    Map<String,String> listaProvince = null;

    public SistemaProvinciale() {
        this.listaProvince = new SistemaProvincialeDAO().getListProvince();
        // To print every provincia in standard error.
      /*  for (Map.Entry<String,String> entry :listaProvince.entrySet()) {
            System.err.println(entry.getKey() +" "+ entry.getValue());
        }

       */
    }
}
