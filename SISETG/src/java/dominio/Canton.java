/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Canton {

    private String IdCanton;
    private String NombreCanton;
    public Canton(String IdCanton, String NombreCanton) {
        this.IdCanton = IdCanton;
        this.NombreCanton = NombreCanton;
    }

    public String getIdCanton() {
        return IdCanton;
    }

    public void setIdCanton(String IdCanton) {
        this.IdCanton = IdCanton;
    }

    public String getNombreCanton() {
        return NombreCanton;
    }

    public void setNombreCanton(String NombreCanton) {
        this.NombreCanton = NombreCanton;
    }
    
}
