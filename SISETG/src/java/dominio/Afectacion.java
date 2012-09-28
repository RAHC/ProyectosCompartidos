/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;

/**
 *
 * @author Familia Molina
 */
public class Afectacion implements Serializable{
    
    private String idAfectacion;
    private String nombreAfectacion;

    public Afectacion(String idAfectacion, String nombreAfectacion) {
        this.idAfectacion = idAfectacion;
        this.nombreAfectacion = nombreAfectacion;
    }

    public String getIdAfectacion() {
        return idAfectacion;
    }

    public void setIdAfectacion(String idAfectacion) {
        this.idAfectacion = idAfectacion;
    }

    public String getNombreAfectacion() {
        return nombreAfectacion;
    }

    public void setNombreAfectacion(String nombreAfectacion) {
        this.nombreAfectacion = nombreAfectacion;
    }
      
}
