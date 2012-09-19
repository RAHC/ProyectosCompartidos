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
public class Evento implements Serializable{
    
    private String IdEvento;
    private String NombreEvento;

    public Evento(String IdEvento, String NombreEvento) {
        this.IdEvento = IdEvento;
        this.NombreEvento = NombreEvento;
    }

    public String getIdEvento() {
        return IdEvento;
    }

    public void setIdEvento(String IdEvento) {
        this.IdEvento = IdEvento;
    }

    public String getNombreEvento() {
        return NombreEvento;
    }

    public void setNombreEvento(String NombreEvento) {
        this.NombreEvento = NombreEvento;
    }
    
    
    
}
