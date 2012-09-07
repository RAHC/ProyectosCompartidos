/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Municipio {

    private String IdMunicipio;
    private String NombreMunicipio;

    public Municipio(String IdMunicipio, String NombreMunicipio) {
        this.IdMunicipio = IdMunicipio;
        this.NombreMunicipio = NombreMunicipio;
    }
    public String getNombreMunicipio() {
        return NombreMunicipio;
    }

    public void setNombreMunicipio(String NombreMunicipio) {
        this.NombreMunicipio = NombreMunicipio;
    }

    public String getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(String IdMunicipio) {
        this.IdMunicipio = IdMunicipio;
    }
    
}
