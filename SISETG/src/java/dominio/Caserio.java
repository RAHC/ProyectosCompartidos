/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Familia Molina
 */
public class Caserio {
    
    private String IdCaserio;
    private String NombreCaserio;
    private Float LongitudCaserio;
    private Float LatitudCaserio;

    public Caserio(String IdCaserio, String NombreCaserio,  Float LatitudCaserio, Float LongitudCaserio) {
        this.IdCaserio = IdCaserio;
        this.NombreCaserio = NombreCaserio;
        this.LongitudCaserio = LongitudCaserio;
        this.LatitudCaserio = LatitudCaserio;
  
    }

    public String getIdCaserio() {
        return IdCaserio;
    }

    public void setIdCaserio(String IdCaserio) {
        this.IdCaserio = IdCaserio;
    }

    public String getNombreCaserio() {
        return NombreCaserio;
    }

    public void setNombreCanserio(String NombreCaserio) {
        this.NombreCaserio = NombreCaserio;
    }

    public Float getLatitudCaserio() {
        return LatitudCaserio;
    }

    public void setLatitudCaserio(Float LatitudCaserio) {
        this.LatitudCaserio = LatitudCaserio;
    }

    public Float getLongitudCaserio() {
        return LongitudCaserio;
    }

    public void setLongitudCaserio(Float LongitudCaserio) {
        this.LongitudCaserio = LongitudCaserio;
    }

}
