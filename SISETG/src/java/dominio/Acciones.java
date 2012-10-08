
package dominio;

import java.util.Date;


public class Acciones {
    private Integer IdAcc;
    private String IdEv;
    private String CorrInc;
    private Integer IdEstado;
    private Integer IdContacto;
    private String DescripAccion;
    private String ResponsableCoord;
    private String FechaRealizacion;
    private String Duracion;
    private String FechaAlmacenado;
    private String EstadoAccion;
    
    public Acciones(Integer IdAcc, String IdEv, String CorrInc, Integer IdEstado, Integer IdContacto, String DescripAccion, String ResponsableCoord, 
            String FechaRealizacion, String Duracion, String FechaAlmacenado, String EstadoAccion) {
        this.IdAcc = IdAcc;
        this.IdEv = IdEv;
        this.CorrInc = CorrInc;
        this.IdEstado = IdEstado;
        this.IdContacto = IdContacto;
        this.DescripAccion = DescripAccion;
        this.ResponsableCoord = ResponsableCoord;
        this.FechaRealizacion = FechaRealizacion;
        this.Duracion = Duracion;
        this.FechaAlmacenado = FechaAlmacenado;
        this.EstadoAccion = EstadoAccion;
    }

    public Integer getIdAcc() {
        return IdAcc;
    }

    public void setIdAcc(Integer IdAcc) {
        this.IdAcc = IdAcc;
    }

    public String getCorrInc() {
        return CorrInc;
    }

    public void setCorrInc(String CorrInc) {
        this.CorrInc = CorrInc;
    }

    public String getDescripAccion() {
        return DescripAccion;
    }

    public void setDescripAccion(String DescripAccion) {
        this.DescripAccion = DescripAccion;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String Duracion) {
        this.Duracion = Duracion;
    }

    public String getEstadoAccion() {
        return EstadoAccion;
    }

    public void setEstadoAccion(String EstadoAccion) {
        this.EstadoAccion = EstadoAccion;
    }

    public String getFechaAlmacenado() {
        return FechaAlmacenado;
    }

    public void setFechaAlmacenado(String FechaAlmacenado) {
        this.FechaAlmacenado = FechaAlmacenado;
    }

    public String getFechaRealizacion() {
        return FechaRealizacion;
    }

    public void setFechaRealizacion(String FechaRealizacion) {
        this.FechaRealizacion = FechaRealizacion;
    }

    public Integer getIdContacto() {
        return IdContacto;
    }

    public void setIdContacto(Integer IdContacto) {
        this.IdContacto = IdContacto;
    }

    public Integer getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(Integer IdEstado) {
        this.IdEstado = IdEstado;
    }

    public String getIdEv() {
        return IdEv;
    }

    public void setIdEv(String IdEv) {
        this.IdEv = IdEv;
    }

    public String getResponsableCoord() {
        return ResponsableCoord;
    }

    public void setResponsableCoord(String ResponsableCoord) {
        this.ResponsableCoord = ResponsableCoord;
    }
    
}
