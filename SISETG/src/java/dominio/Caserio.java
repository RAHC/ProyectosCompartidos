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
    private String NombreCanserio;

    public Caserio(String IdCaserio, String NombreCanserio) {
        this.IdCaserio = IdCaserio;
        this.NombreCanserio = NombreCanserio;
    }

    public String getIdCaserio() {
        return IdCaserio;
    }

    public void setIdCaserio(String IdCaserio) {
        this.IdCaserio = IdCaserio;
    }

    public String getNombreCanserio() {
        return NombreCanserio;
    }

    public void setNombreCanserio(String NombreCanserio) {
        this.NombreCanserio = NombreCanserio;
    }
    
    
    
}
