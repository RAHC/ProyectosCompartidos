package dominio;

import java.io.Serializable;
import java.util.Date;

public class Incidente implements Serializable{
    private String IdTipoIncidente;
    private Date fechaHora;
    private String Direccion;
    private String puntoReferencia;
    private int Idestado;

    public Incidente(int estado,String IdTipoIncidente, Date fechaHora, String Direccion, String puntoReferencia) {
        this.Idestado=estado;
        this.IdTipoIncidente = IdTipoIncidente;
        this.fechaHora = fechaHora;
        this.Direccion = Direccion;
        this.puntoReferencia = puntoReferencia;
    }

    public int getIdestado() {
        return Idestado;
    }

    public void setIdestado(int Idestado) {
        this.Idestado = Idestado;
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
