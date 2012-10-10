/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

//import Mapa.DatosMapa;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

//import model.GeneralModel;
/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class RegistrarInstitucion implements Serializable {

    private int IdInstitucion;
    private String NombreInstitucion;
    private String NombreResponsable;
    private String DirInstitucion;
    private String TelInstitucion;
    private Double LatInst;
    private Double LngInst;
    private Double AltInst;
    private String PnRefInst;
    private String CodDepartamento;
    private String CodMunicipio;
    private String CodCanton;
    private String CodCaserio;
    private double lat;
    private double lng;
    private double altu;
    private MapModel draggableModel;
    private String title;
    private int TpInstitucion;
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public RegistrarInstitucion() {
        draggableModel = new DefaultMapModel();
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMarker(ActionEvent actionEvent) {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        marker.setDraggable(false);
        draggableModel.addOverlay(marker);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca agregada", "Lat:" + lat + ", Lng:" + lng + ", \nAltura: " + altu + " Metros Sobre el Nivel del Mar"));
        for (Marker marker1 : draggableModel.getMarkers()) {
            marker1.setDraggable(false);
        }


    }

    public void onMarkerDrag(MarkerDragEvent event) {
        Marker marker = event.getMarker();
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca trasladada", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng() + ", \nAltura: " + altu + " Metros"));
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca Seleccionada", marker.getTitle()));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public void setDraggableModel(MapModel draggableModel) {
        this.draggableModel = draggableModel;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getAltu() {
        return altu;
    }

    public void setAltu(double altu) {
        this.altu = altu;
    }

    public int getTpInstitucion() {
        return TpInstitucion;
    }

    public void setTpInstitucion(int TpInstitucion) {
        this.TpInstitucion = TpInstitucion;
    }

    public String getNombreInstitucion() {
        return NombreInstitucion;
    }

    public void setNombreInstitucion(String NombreInstitucion) {
        this.NombreInstitucion = NombreInstitucion;
    }

    public String getNombreResponsable() {
        return NombreResponsable;
    }

    public void setNombreResponsable(String NombreResponsable) {
        this.NombreResponsable = NombreResponsable;
    }

    public String getDirInstitucion() {
        return DirInstitucion;
    }

    public void setDirInstitucion(String DirInstitucion) {
        this.DirInstitucion = DirInstitucion;
    }

    public String getTelInstitucion() {
        return TelInstitucion;
    }

    public void setTelInstitucion(String TelInstitucion) {
        this.TelInstitucion = TelInstitucion;
    }

    public String getPnRefInst() {
        return PnRefInst;
    }

    public void setPnRefInst(String PnRefInst) {
        this.PnRefInst = PnRefInst;
    }

    public String getCodDepartamento() {
        return CodDepartamento;
    }

    public void setCodDepartamento(String CodDepartamento) {
        this.CodDepartamento = CodDepartamento;
    }

    public String getCodMunicipio() {
        return CodMunicipio;
    }

    public void setCodMunicipio(String CodMunicipio) {
        this.CodMunicipio = CodMunicipio;
    }

    public String getCodCanton() {
        return CodCanton;
    }

    public void setCodCanton(String CodCanton) {
        this.CodCanton = CodCanton;
    }

    public String getCodCaserio() {
        return CodCaserio;
    }

    public void setCodCaserio(String CodCanton) {
        this.CodCanton = CodCanton;
    }

    public void recargar() {
        RequestContext context = RequestContext.getCurrentInstance();

        //execute javascript oncomplete
        context.execute("PrimeFaces.info('Hello from the Backing Bean');");

        //update panel
        context.update("form.panel");
    }

    public Float getLatitud() throws SQLException {
        Float Latitud;
        if (CodDepartamento == null) {
            Latitud = (float) 13.70;
        } else {
            String id = null;
            if (CodCaserio != null) {
                id = CodCaserio;
            } else if (CodCanton != null) {
                id = CodCanton;
            } else if (CodMunicipio != null) {
                id = CodMunicipio;
            } else if (CodDepartamento != null) {
                id = CodDepartamento;
            }
            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();
            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }
            PreparedStatement getLatitudes = connection.prepareStatement(
                    "SELECT LATITUDUBIC FROM UBICACION WHERE IDUBIC='" + id + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getLatitudes.executeQuery());
            rowSet.next();
            Latitud = Float.parseFloat(rowSet.getString("LATITUDUBIC"));

            connection.close();
        }
        return Latitud;
    }

    public Float getLongitud() throws SQLException {
        Float Longitud;
        if (CodDepartamento == null) {
            Longitud = (float) -88.91;
        } else {
            String id = null;
            if (CodCaserio != null) {
                id = CodCaserio;
            } else if (CodCanton != null) {
                id = CodCanton;
            } else if (CodMunicipio != null) {
                id = CodMunicipio;
            } else if (CodDepartamento != null) {
                id = CodDepartamento;
            }
            if (dataSource == null) {
                throw new SQLException("No se pudo tener acceso a la fuente de datos");
            }
            Connection connection = dataSource.getConnection();
            if (connection == null) {
                throw new SQLException("No se pudo conectar a la fuente de datos");
            }
            PreparedStatement getLongitudes = connection.prepareStatement(
                    "SELECT LONGITUDUBIC FROM UBICACION WHERE IDUBIC='" + id + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getLongitudes.executeQuery());
            rowSet.next();
            Longitud = Float.parseFloat(rowSet.getString("LONGITUDUBIC"));
            connection.close();
        }
        return Longitud;
    }

    public Integer getZoomUbic() {
        Integer z;
        if (CodCaserio != null) {
            z = 15;
        } else if (CodCanton != null) {
            z = 13;
        } else if (CodMunicipio != null) {
            z = 12;
        } else if (CodDepartamento != null) {
            z = 10;
        } else {
            z = 8;
        }
        return z;
    }

    public List<TpInstitucion> getTpInstituciones() throws SQLException {
        List<TpInstitucion> resultados = new ArrayList<TpInstitucion>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getTpInstitucion = connection.prepareStatement(
                    "SELECT IDTPINST, NOMBTPINST, DESCTPINST FROM TIPOINSTITUCION");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTpInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new TpInstitucion(rowSet.getInt("IDTPINST"),
                        rowSet.getString("NOMBTPINST"),
                        rowSet.getString("DESCTPINST")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Institucion> getInstituciones() throws SQLException {
        List<Institucion> resultados = new ArrayList<Institucion>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getInstitucion = connection.prepareStatement(
                    "SELECT IDINST, NOMBINST FROM INSTITUCION WHERE IDTPINST= " + TpInstitucion);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Institucion(rowSet.getInt("IDINST"),
                        rowSet.getString("NOMBINST")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Departamento> getDepartamentos() throws SQLException {
        List<Departamento> resultados = new ArrayList<Departamento>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getDepartamento = connection.prepareStatement(
                    "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE is NULL order by NOMBUBIC");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getDepartamento.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Departamento(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Municipio> getMunicipios() throws SQLException {
        List<Municipio> resultados = new ArrayList<Municipio>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodDepartamento + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Municipio(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Canton> getCantones() throws SQLException {
        List<Canton> resultados = new ArrayList<Canton>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC  FROM UBICACION WHERE IDUBIC_PADRE = '" + CodMunicipio + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Canton(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Caserio> getCaserios() throws SQLException {
        List<Caserio> resultados = new ArrayList<Caserio>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC  FROM UBICACION WHERE IDUBIC_PADRE = '" + CodCanton + "' order by NOMBUBIC";
            PreparedStatement getUbicacion = connection.prepareStatement(query);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getUbicacion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new Caserio(rowSet.getString("IDUBIC"),
                        rowSet.getString("NOMBUBIC"),
                        rowSet.getFloat("LATITUDUBIC"),
                        rowSet.getFloat("LONGITUDUBIC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public String guardarInst(/*ActionEvent e*/) throws SQLException {
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        
        try {
            CallableStatement inst;
            String sql = "{ call INSTITUCION_Add(?,?,?,?,?,?,?,?,?,?)}";
            inst = connection.prepareCall(sql);
            inst.setString(1, getCodCanton());
            inst.setInt(2, getTpInstitucion());
            inst.setString(3, getNombreInstitucion());
            inst.setString(4, getNombreResponsable());
            inst.setString(5, getDirInstitucion());
            inst.setString(6, getTelInstitucion());
            inst.setDouble(7, lat);
            inst.setDouble(8, lng);
            inst.setDouble(9, altu);
            inst.setString(10, getPnRefInst());

            inst.execute();
            inst.close();
        } finally {
            connection.close();
        }
        return "pruebaRegistroInstitucion.xhtml";
    }
}
