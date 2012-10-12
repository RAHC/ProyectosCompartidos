package dominio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class EntiResp {

    private int idEntiResp;
    private String nombreEntiResp;

    public EntiResp(int idEntiResp, String nombreEntiResp) {
        this.idEntiResp = idEntiResp;
        this.nombreEntiResp = nombreEntiResp;
    }

    public int getIdEntiResp() {
        return idEntiResp;
    }

    public void setIdEntiResp(int idEntiResp) {
        this.idEntiResp = idEntiResp;
    }

    public String getNombreEntiResp() {
        return nombreEntiResp;
    }

    public void setNombreEntiResp(String nombreEntiResp) {
        this.nombreEntiResp = nombreEntiResp;
    }

}
