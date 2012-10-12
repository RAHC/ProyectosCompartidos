package dominio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class TpSanitario {
    
    private int idTpSanitario;
    private String nombTpSanitario;

    public TpSanitario(int idTpSanitario, String nombTpSanitario) {
        this.idTpSanitario = idTpSanitario;
        this.nombTpSanitario = nombTpSanitario;
    }

    public int getIdTpSanitario() {
        return idTpSanitario;
    }

    public void setIdTpSanitario(int idTpSanitario) {
        this.idTpSanitario = idTpSanitario;
    }

    public String getNombTpSanitario() {
        return nombTpSanitario;
    }

    public void setNombTpSanitario(String nombTpSanitario) {
        this.nombTpSanitario = nombTpSanitario;
    }    
}
