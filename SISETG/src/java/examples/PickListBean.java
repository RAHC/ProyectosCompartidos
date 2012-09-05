/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.util.List;

/**
 *
 * @author Administrador
 */
public class PickListBean {
    private final List<Instituciones> Existentes;
    private final List<Instituciones> Clasificadas;
    private final String identificador;
    
    public PickListBean(final String identificador, final List<Instituciones> Existentes, final List<Instituciones> Clasificadas ){
        this.identificador = identificador;
        this.Existentes = Existentes;
        this.Clasificadas = Clasificadas;
    }

    public List<Instituciones> getClasificadas() {
        return Clasificadas;
    }

    public List<Instituciones> getExistentes() {
        return Existentes;
    }

    public String getIdentificador() {
        return identificador;
    }
    
}
