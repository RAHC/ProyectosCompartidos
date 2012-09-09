/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Municipio {

    private String IdMunicipio;
    private String NombreMunicipio;
    private Float LongitudMunicipio;
    private Float LatitudMunicipio;

    public Municipio(String IdMunicipio, String NombreMunicipio, Float LongitudMunicipio, Float LatitudMunicipio) {
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

    public Float getLatitudMunicipio() {
        return LatitudMunicipio;
    }

    public void setLatitudMunicipio(Float LatitudMunicipio) {
        this.LatitudMunicipio = LatitudMunicipio;
    }

    public Float getLongitudMunicipio() {
        return LongitudMunicipio;
    }

    public void setLongitudMunicipio(Float LongitudMunicipio) {
        this.LongitudMunicipio = LongitudMunicipio;
    }
    
}
