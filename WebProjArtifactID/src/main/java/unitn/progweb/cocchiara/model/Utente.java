package unitn.progweb.cocchiara.model;

import org.jetbrains.annotations.NotNull;
import unitn.progweb.cocchiara.dao.UtenteDAO;

public class Utente {
    Paziente paziente = null;
    Medico medico = null;
    Admin admin = null;

    public Paziente getPaziente() {
        return paziente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Admin getAdmin() {
        return admin;
    }
    public Boolean isMedico() {
        return medico!=null;
    }
    public Boolean isAdmin() {
        return  admin!=null;
    }
    public Utente(@NotNull Paziente paziente, Medico medico, Admin admin) {
        this.paziente = paziente;
        this.medico = medico;
        this.admin = admin;
    }
    public Utente() {

    }

    public boolean changePassword(@NotNull String password, @NotNull String newpassword) {
        if(newpassword.length() < 8)
            return false;

        UtenteDAO pd = new UtenteDAO();
        return pd.changePassword(paziente.codicefiscale,password,newpassword);
    }
}
