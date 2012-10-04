/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Administrador
 */
public class Departamento {
    
    private String idDepartamento;
    private String nombreDepartamento;
    private Float LongitudDepartamento;
    private Float LatitudDepartamento;

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String IdDepartamento) {
        this.idDepartamento = IdDepartamento;
    }

    public Float getLatitudDepartamento() {
        return LatitudDepartamento;
    }

    public void setLatitudDepartamento(Float LatitudDepartamento) {
        this.LatitudDepartamento = LatitudDepartamento;
    }

    public Float getLongitudDepartamento() {
        return LongitudDepartamento;
    }

    public void setLongitudDepartamento(Float LongitudDepartamento) {
        this.LongitudDepartamento = LongitudDepartamento;
    }
    
    public Departamento(String idDepartamento, String nombreDepartamento, Float LatitudDepartamento, Float LongitudDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }
}
