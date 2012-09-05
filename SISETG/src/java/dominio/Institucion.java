/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Institucion {

    private int IdInstitucion;
    private String NombreInstitucion;

    public Institucion(int IdInstitucion, String NombreInstitucion) {
        this.IdInstitucion = IdInstitucion;
        this.NombreInstitucion = NombreInstitucion;
    }
    public String getNombreInstitucion() {
        return NombreInstitucion;
    }

    public void setNombreInstitucion(String NombreInstitucion) {
        this.NombreInstitucion = NombreInstitucion;
    }
     public int getIdInstitucion() {
        return IdInstitucion;
    }

    public void setIdInstitucion(int IdInstitucion) {
        this.IdInstitucion = IdInstitucion;
    }
}
