package unitn.progweb.cocchiara.model;

import org.jetbrains.annotations.NotNull;
import unitn.progweb.cocchiara.dao.UtenteDAO;

import java.util.ArrayList;

public class Utente {
    Paziente paziente = null;
    Medico medico = null;
    Boolean isAdmin = false;

    public Paziente getPaziente() {
        return paziente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public Boolean isMedico() {
        return medico!=null;
    }
    public Boolean isAdmin() {
        return isAdmin;
    }
    public Utente(@NotNull Paziente paziente, Medico medico, Boolean admin) {
        this.paziente = paziente;
        this.medico = medico;
        this.isAdmin = admin;
    }
    public Utente() {

    }

    public boolean changePassword(@NotNull String password, @NotNull String newpassword) {
        if(newpassword.length() < 8)
            return false;

        UtenteDAO pd = new UtenteDAO();
        return pd.changePassword(paziente.codicefiscale,password,newpassword);
    }

    public void deleteCookie() {
        UtenteDAO ud = new UtenteDAO();
        ud.deleteCookieForUser(paziente.codicefiscale);
    }

    static public class Notifica
    {

        int id;
        String text;

        public Notifica(int id, String text) {
            this.id = id;
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public String getText() {
            return text;
        }


    }

    public ArrayList<Notifica> getNotifiche()
    {
        return new UtenteDAO().getNotifiche(paziente.codicefiscale);
    }

    public Boolean deleteNotifica(int idNotifica)
    {
        return new UtenteDAO().deleteNotifica(paziente.codicefiscale,idNotifica);
    }
}
