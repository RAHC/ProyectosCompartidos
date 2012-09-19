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
public class TipoIncidente implements Serializable{
    
    private String idTipoIncidente;
    private String nombre;

    public TipoIncidente(String idTipoIncidente, String nombre) {
        this.idTipoIncidente = idTipoIncidente;
        this.nombre = nombre;
    }
    

    public String getIdTipoIncidente() {
        return idTipoIncidente;
    }

    public void setIdTipoIncidente(String idTipoIncidente) {
        this.idTipoIncidente = idTipoIncidente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
