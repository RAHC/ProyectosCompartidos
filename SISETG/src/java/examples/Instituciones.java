/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;


public class Instituciones {
    private final Integer Identificador;
    private final String NombreInstitucion;
    
    public Instituciones(final Integer Identificador, final String NombreInstitucion){
        this.Identificador = Identificador;
        this.NombreInstitucion = NombreInstitucion;
    }

    public Integer getIdentificador() {
        return Identificador;
    }

    public String getNombreInstitucion() {
        return NombreInstitucion;
    }
    
}
