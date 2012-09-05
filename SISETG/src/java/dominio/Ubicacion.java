/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;


public class Ubicacion {

    private String IdUbicacion;
    private String NombreUbicacion;
   
    public Ubicacion(String IdUbicacion, String NombreUbicacion) {
        this.IdUbicacion = IdUbicacion;
        this.NombreUbicacion = NombreUbicacion;
    }
     public String getIdUbicacion() {
        return IdUbicacion;
    }

    public void setIdUbicacion(String IdUbicacion) {
        this.IdUbicacion = IdUbicacion;
    }
    public String getNombreUbicacion() {
        return NombreUbicacion;
    }

    public void setNombreUbicacion(String NombreUbicacion) {
        this.NombreUbicacion = NombreUbicacion;
    }
}
