/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Canton {

    private String IdCanton;
    private String NombreCanton;
    private Float LongitudCanton;
    private Float LatitudCanton;
    
    public Canton(String IdCanton, String NombreCanton, Float LongitudCanton, Float LatitudCanton) {
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
    public Float getLatitudCanton() {
        return LatitudCanton;
    }

    public void setLatitudCanton(Float LatitudCanton) {
        this.LatitudCanton = LatitudCanton;
    }

    public Float getLongitudCanton() {
        return LongitudCanton;
    }

    public void setLongitudCanton(Float LongitudCanton) {
        this.LongitudCanton = LongitudCanton;
    }
    
}
