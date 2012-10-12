package dominio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class Servicio {
    private int idServ;
    private String nombServ;

    public Servicio(int idServ, String nombServ) {
        this.idServ = idServ;
        this.nombServ = nombServ;
    }
    
    public int getIdServ() {
        return idServ;
    }

    public void setIdServ(int idServ) {
        this.idServ = idServ;
    }

    public String getNombServ() {
        return nombServ;
    }

    public void setNombServ(String nombServ) {
        this.nombServ = nombServ;
    }
    
    
}
