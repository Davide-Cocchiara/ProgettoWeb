package unitn.progweb.cocchiara.model;

import org.jetbrains.annotations.NotNull;
import unitn.progweb.cocchiara.dao.PazienteDAO;
import unitn.progweb.cocchiara.dao.SistemaProvincialeDAO;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Map;

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
    public LinkedHashMap<String,String> getListaEsamiDisponibili(@NotNull Boolean diLaboratorio) {
        return new SistemaProvincialeDAO().getListaEsamiFromProvincia(provincia, diLaboratorio);
    }

    public ArrayList<Map.Entry<String, Pagamento>> getReportPrestazioniErogateFromProvincia(@NotNull  int tipo) {
        return new SistemaProvincialeDAO().getReportPrestazioniErogateFromProvincia(provincia, tipo);
    }

    public Paziente getPazienteOfProvincia(String codicepaziente) {
        PazienteDAO pd = new PazienteDAO();
        Paziente p = pd.getUserFromCodice(codicepaziente);
        if (p != null && p.provincia.equals(provincia))
            return p;
        else
            return null;

    }

    public ArrayList<Paziente> getListaPazienti() {
        return new SistemaProvincialeDAO().getListaPazientiMinimaleFromProvincia(provincia);
    }

    public Boolean erogaFarmaco(Integer idprescrizione) {
        return new SistemaProvincialeDAO().erogaRicetta(provincia, idprescrizione,true);
    }
}
