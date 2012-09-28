/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;

/**
 *
 * @author Familia Molina
 */
public class AfectacionAPersona implements Serializable{
    private int idAfecPer;
    private String idTPAfectacion; 
    private String nombreAfectacion;
    private String nombreAfectado;
    private int edadAfectado;
    private String generoAfectado;
    private String esDiscapacitado;
    private String centroAsistencial;

    public AfectacionAPersona(int idAfecPer, String idTPAfectacion, String nombreAfectacion, String nombreAfectado, int edadAfectado, String generoAfectado, String esDiscapacitado, String centroAsistencial) {
        this.idAfecPer = idAfecPer;
        this.idTPAfectacion = idTPAfectacion;
        this.nombreAfectacion = nombreAfectacion;
        this.nombreAfectado = nombreAfectado;
        this.edadAfectado = edadAfectado;
        this.generoAfectado = generoAfectado;
        this.esDiscapacitado = esDiscapacitado;
        this.centroAsistencial = centroAsistencial;
    }


    public String getIdTPAfectacion() {
        return idTPAfectacion;
    }

    public void setIdTPAfectacion(String idTPAfectacion) {
        this.idTPAfectacion = idTPAfectacion;
    }

    public int getIdAfecPer() {
        return idAfecPer;
    }

    public void setIdAfecPer(int idAfecPer) {
        this.idAfecPer = idAfecPer;
    }

    
    public String getNombreAfectacion() {
        return nombreAfectacion;
    }

    public void setNombreAfectacion(String nombreAfectacion) {
        this.nombreAfectacion = nombreAfectacion;
    }

    public String getNombreAfectado() {
        return nombreAfectado;
    }

    public void setNombreAfectado(String nombreAfectado) {
        this.nombreAfectado = nombreAfectado;
    }

    public int getEdadAfectado() {
        return edadAfectado;
    }

    public void setEdadAfectado(int edadAfectado) {
        this.edadAfectado = edadAfectado;
    }

    public String getGeneroAfectado() {
        return generoAfectado;
    }

    public void setGeneroAfectado(String generoAfectado) {
        this.generoAfectado = generoAfectado;
    }

    public String getEsDiscapacitado() {
        return esDiscapacitado;
    }

    public void setEsDiscapacitado(String esDiscapacitado) {
        this.esDiscapacitado = esDiscapacitado;
    }

    public String getCentroAsistencial() {
        return centroAsistencial;
    }

    public void setCentroAsistencial(String centroAsistencial) {
        this.centroAsistencial = centroAsistencial;
    }

   
    
}
