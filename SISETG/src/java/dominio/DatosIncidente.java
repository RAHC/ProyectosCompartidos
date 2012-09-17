package dominio;

import java.util.List;
import org.primefaces.model.map.MapModel;

public class DatosIncidente{

    private String IdEv;
    private String CorrInc;
    private Integer IdPrioridad;
    private Integer IdEstado;
    private String IdTipoIncidente;
    private Integer IdInformante;
    private String IdUbicacion;
    private String FechaIncidente;
    private String HoraIncidente;
    private Float Latitud;
    private Float Longitud;
    private Float Altimetria;
    private String Descripcion;
    private String Direccion;
    private String PtoReferencia;
    private String NombrePrioridad;
    private String NombreUbicacion;
    private Float LatitudUbicacion;
    private Float LongitudUbicacion;
    private String NombreEstado;
    private String NombreTipoIncidente;
    private String NombreInformante;
    private String ApellidoInformante;
    private String TelInformante;
    private String FechaNotificacion;
    private String HoraNotificacion;
    private List<Acciones> ListaAcciones;
    private MapModel MarkerInc;

    public DatosIncidente(String IdEv, String CorrInc, Integer IdPrioridad, Integer IdEstado, String IdTipoIncidente,
            Integer IdInformante, String IdUbicacion, String FechaIncidente, String HoraIncidente, Float Latitud,
            Float Longitud, Float Altimetria, String Descripcion, String Direccion,
            String PtoReferencia, String NombrePrioridad, String NombreUbicacion, Float LatitudUbicacion, Float LongitudUbicacion, String NombreEstado,
            String NombreTipoIncidente, String NombreInformante, String ApellidoInformante, String TelInformante, 
            String FechaNotificacion, String HoraNotificacion, List<Acciones> ListaAcciones, MapModel MarkerInc) {
        this.IdEv = IdEv;
        this.CorrInc = CorrInc;
        this.IdEstado = IdEstado;
        this.IdTipoIncidente = IdTipoIncidente;
        this.IdInformante = IdInformante;
        this.IdUbicacion = IdUbicacion;
        this.FechaIncidente = FechaIncidente;
        this.HoraIncidente = HoraIncidente;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
        this.Altimetria = Altimetria;
        this.Descripcion = Descripcion;
        this.Direccion = Direccion;
        this.PtoReferencia = PtoReferencia;
        this.NombrePrioridad = NombrePrioridad;
        this.NombreUbicacion = NombreUbicacion;
        this.LatitudUbicacion = LatitudUbicacion;
        this.LongitudUbicacion = LongitudUbicacion;
        this.NombreEstado = NombreEstado;
        this.NombreTipoIncidente = NombreTipoIncidente;
        this.NombreInformante = NombreInformante;
        this.ApellidoInformante = ApellidoInformante;
        this.TelInformante = TelInformante;
        this.FechaNotificacion = FechaNotificacion;
        this.HoraNotificacion = HoraNotificacion;
        this.ListaAcciones = ListaAcciones;
        this.MarkerInc = MarkerInc;
    }

    public Float getAltimetria() {
        return Altimetria;
    }

    public void setAltimetria(Float Altimetria) {
        this.Altimetria = Altimetria;
    }

    public String getApellidoInformante() {
        return ApellidoInformante;
    }

    public void setApellidoInformante(String ApellidoInformante) {
        this.ApellidoInformante = ApellidoInformante;
    }

    public String getCorrInc() {
        return CorrInc;
    }

    public void setCorrInc(String CorrInc) {
        this.CorrInc = CorrInc;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getFechaIncidente() {
        return FechaIncidente;
    }

    public void setFechaIncidente(String FechaIncidente) {
        this.FechaIncidente = FechaIncidente;
    }

    public String getHoraIncidente() {
        return HoraIncidente;
    }

    public void setHoraIncidente(String HoraIncidente) {
        this.HoraIncidente = HoraIncidente;
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

    public String getIdTipoIncidente() {
        return IdTipoIncidente;
    }

    public void setIdTipoIncidente(String IdTipoIncidente) {
        this.IdTipoIncidente = IdTipoIncidente;
    }

    public String getIdUbicacion() {
        return IdUbicacion;
    }

    public void setIdUbicacion(String IdUbicacion) {
        this.IdUbicacion = IdUbicacion;
    }

    public Float getLatitud() {
        return Latitud;
    }

    public void setLatitud(Float Latitud) {
        this.Latitud = Latitud;
    }

    public Float getLongitud() {
        return Longitud;
    }

    public void setLongitud(Float Longitud) {
        this.Longitud = Longitud;
    }

    public String getNombreEstado() {
        return NombreEstado;
    }

    public void setNombreEstado(String NombreEstado) {
        this.NombreEstado = NombreEstado;
    }

    public String getNombreInformante() {
        return NombreInformante;
    }

    public void setNombreInformante(String NombreInformante) {
        this.NombreInformante = NombreInformante;
    }

    public String getNombrePrioridad() {
        return NombrePrioridad;
    }

    public void setNombrePrioridad(String NombrePrioridad) {
        this.NombrePrioridad = NombrePrioridad;
    }

    public String getNombreTipoIncidente() {
        return NombreTipoIncidente;
    }

    public void setNombreTipoIncidente(String NombreTipoIncidente) {
        this.NombreTipoIncidente = NombreTipoIncidente;
    }

    public String getNombreUbicacion() {
        return NombreUbicacion;
    }

    public void setNombreUbicacion(String NombreUbicacion) {
        this.NombreUbicacion = NombreUbicacion;
    }

    public Float getLatitudUbicacion() {
        return LatitudUbicacion;
    }

    public void setLatitudUbicacion(Float LatitudUbicacion) {
        this.LatitudUbicacion = LatitudUbicacion;
    }

    public Float getLongitudUbicacion() {
        return LongitudUbicacion;
    }

    public void setLongitudUbicacion(Float LongitudUbicacion) {
        this.LongitudUbicacion = LongitudUbicacion;
    }
    
    public String getPtoReferencia() {
        return PtoReferencia;
    }

    public void setPtoReferencia(String PtoReferencia) {
        this.PtoReferencia = PtoReferencia;
    }

    public Integer getIdPrioridad() {
        return IdPrioridad;
    }

    public void setIdPrioridad(Integer IdPrioridad) {
        this.IdPrioridad = IdPrioridad;
    }

    public Integer getIdInformante() {
        return IdInformante;
    }

    public void setIdInformante(Integer IdInformante) {
        this.IdInformante = IdInformante;
    }

    public String getTelInformante() {
        return TelInformante;
    }

    public void setTelInformante(String TelInformante) {
        this.TelInformante = TelInformante;
    }

    public String getFechaNotificacion() {
        return FechaNotificacion;
    }

    public void setFechaNotificacion(String FechaNotificacion) {
        this.FechaNotificacion = FechaNotificacion;
    }

    public String getHoraNotificacion() {
        return HoraNotificacion;
    }

    public void setHoraNotificacion(String HoraNotificacion) {
        this.HoraNotificacion = HoraNotificacion;
    }

    public List<Acciones> getListaAcciones() {
        return ListaAcciones;
    }

    public void setListaAcciones(List<Acciones> ListaAcciones) {
        this.ListaAcciones = ListaAcciones;
    }

    public MapModel getMarkerInc() {
        return MarkerInc;
    }

    public void setMarkerInc(MapModel MarkerInc) {
        this.MarkerInc = MarkerInc;
    }
}
