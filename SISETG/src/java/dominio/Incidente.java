package dominio;

import java.io.Serializable;
import java.util.Date;

public class Incidente implements Serializable{
    private String IdTipoIncidente;
    private Date fechaHora;
    private String Direccion;
    private String puntoReferencia;

    public Incidente(String IdTipoIncidente, Date fechaHora, String Direccion, String puntoReferencia) {
        this.IdTipoIncidente = IdTipoIncidente;
        this.fechaHora = fechaHora;
        this.Direccion = Direccion;
        this.puntoReferencia = puntoReferencia;
    }
    
    public String getIdTipoIncidente() {
        return IdTipoIncidente;
    }

    public void setIdTipoIncidente(String IdTipoIncidente) {
        this.IdTipoIncidente = IdTipoIncidente;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getPuntoReferencia() {
        return puntoReferencia;
    }

    public void setPuntoReferencia(String puntoReferencia) {
        this.puntoReferencia = puntoReferencia;
    }
    
    
    
}
