package dominio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JARG
 */
public class TpAlbergue {
    
    
    
    private String idTpAlbergue;
    private String nombTpAlbergue;

    public TpAlbergue(String idTpAlbergue, String nombTpAlbergue) {
        this.idTpAlbergue = idTpAlbergue;
        this.nombTpAlbergue = nombTpAlbergue;
    }

    public String getIdTpAlbergue() {
        return idTpAlbergue;
    }

    public void setIdTpAlbergue(String idTpAlbergue) {
        this.idTpAlbergue = idTpAlbergue;
    }

    public String getNombTpAlbergue() {
        return nombTpAlbergue;
    }

    public void setNombTpAlbergue(String nombTpAlbergue) {
        this.nombTpAlbergue = nombTpAlbergue;
    }

    
}
