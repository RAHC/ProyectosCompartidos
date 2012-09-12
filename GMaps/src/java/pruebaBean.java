/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Familia Molina
 */
@ManagedBean
@SessionScoped
public class pruebaBean {
    
    private String saludoBienvenida;
    private String saludoDespedida;
    

    /**
     * Creates a new instance of pruebaBean
     */
    public pruebaBean() {
    }

    public String getSaludoBienvenida() {
        return saludoBienvenida;
    }

    public void setSaludoBienvenida(String saludoBienvenida) {
        this.saludoBienvenida = saludoBienvenida;
    }

    public String getSaludoDespedida() {
        return saludoDespedida;
    }

    public void setSaludoDespedida(String saludoDespedida) {
        this.saludoDespedida = saludoDespedida;
    }
    
    
}
