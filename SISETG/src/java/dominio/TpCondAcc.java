package dominio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class TpCondAcc {
    
    private int idCondAcce;
    private String nombCondAcce;

    public TpCondAcc(int idCondAcce, String nombCondAcce) {
        this.idCondAcce = idCondAcce;
        this.nombCondAcce = nombCondAcce;
    }

    public int getIdCondAcce() {
        return idCondAcce;
    }

    public void setIdCondAcce(int idCondAcce) {
        this.idCondAcce = idCondAcce;
    }

    public String getNombCondAcce() {
        return nombCondAcce;
    }

    public void setNombCondAcce(String nombCondAcce) {
        this.nombCondAcce = nombCondAcce;
    }
    
    
}
