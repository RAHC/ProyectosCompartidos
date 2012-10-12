package dominio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class OtroServicioAgua {
    private int idOtroServicioAgua;
    private String nombOtroServicioAgua;

    public OtroServicioAgua(int idOtroServicioAgua, String nombOtroServicioAgua) {
        this.idOtroServicioAgua = idOtroServicioAgua;
        this.nombOtroServicioAgua = nombOtroServicioAgua;
    }

    public int getIdOtroServicioAgua() {
        return idOtroServicioAgua;
    }

    public void setIdOtroServicioAgua(int idOtroServicioAgua) {
        this.idOtroServicioAgua = idOtroServicioAgua;
    }

    public String getNombOtroServicioAgua() {
        return nombOtroServicioAgua;
    }

    public void setNombOtroServicioAgua(String nombOtroServicioAgua) {
        this.nombOtroServicioAgua = nombOtroServicioAgua;
    }
           
}
