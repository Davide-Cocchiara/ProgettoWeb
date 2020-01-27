package unitn.progweb.cocchiara.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unitn.progweb.cocchiara.dao.MedicoDAO;
import unitn.progweb.cocchiara.dao.PazienteDAO;

import java.util.*;
import java.util.ArrayList;

public class Medico {
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

    public boolean setInfoClinica(@NotNull String codiceFiscale, @NotNull String indirizzo, @NotNull String numero) {

        MedicoDAO md = new MedicoDAO();
        if (md.setInfoClinica(codiceFiscale, indirizzo, numero)) {
            this.clinica = indirizzo;
            this.telefonoclinica = numero;
            return true;
        } else
            return false;
    }


    public ArrayList<Paziente> getListPazienti(@NotNull String codiceMedico) {
        MedicoDAO md = new MedicoDAO();
        return md.getListaPazientiMinimale(codiceMedico);
    }

    public Paziente getOwnPaziente(@NotNull String codiceMedico, @NotNull String s_selectedpaziente) {
        PazienteDAO pd = new PazienteDAO();
        Paziente p = pd.getUserFromCodice(s_selectedpaziente);
        if (p != null && p.medicoAssegnato.equals(codiceMedico))
            return p;
        else
            return null;
    }

    public Boolean addReferto(@NotNull String codicemedico, @NotNull String prestazione, @NotNull String relazione, @NotNull String codicefiscalePziente, @NotNull Boolean isPagato) {
        MedicoDAO md = new MedicoDAO();
        return md.addReferto(codicemedico, prestazione, relazione, codicefiscalePziente, isPagato);
    }

    public LinkedHashMap<String, Map.Entry<String,String>> getListEsamiRefertabiliPaziente(@NotNull String codicepaziente, @NotNull String provincia) {
        return new MedicoDAO().getListEsamiRefertabiliPaziente(codicepaziente, provincia);
    }

    // Esame / Farmaco / Etc
    public Boolean addRicetta(@NotNull String codicemedico, @NotNull String provinciaRilascio, String idesame, String codicefiscalePaziente) {
        return new MedicoDAO().addRicetta(codicemedico, provinciaRilascio, idesame, codicefiscalePaziente);
    }


}