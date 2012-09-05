/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Familia Molina
 */
public class Prioridad {
    private int IdPrioridad;
    private String nombrePrioridad;
    private String descripcionPrioridad;

    
    public Prioridad(int id, String nombre,String descripcion){
        this.IdPrioridad=id;
        this.nombrePrioridad=nombre;
        this.descripcionPrioridad=descripcion;
    }
    
    
    public int getIdPrioridad() {
        return IdPrioridad;
    }

    public void setIdPrioridad(int IdPrioridad) {
        this.IdPrioridad = IdPrioridad;
    }

    public String getDescripcionPrioridad() {
        return descripcionPrioridad;
    }

    public void setDescripcionPrioridad(String descripcionPrioridad) {
        this.descripcionPrioridad = descripcionPrioridad;
    }

    public String getNombrePrioridad() {
        return nombrePrioridad;
    }

    public void setNombrePrioridad(String nombrePrioridad) {
        this.nombrePrioridad = nombrePrioridad;
    }
    
    
}
