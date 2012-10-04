/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
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
public class init {

    private String IdCatIncidente = "";
    private String IdTipoIncidente = "";
    private String IdIncidente = "";
    private String CodDepartamento;
    private String CodMunicipio;
    private String CodCanton;
    private String CodCaserio;
    private double lat;
    private double lng;
    private String title;
    private MapModel draggableModel;
    private int TpInstitucion;
    private String IdUbicacion;
    private String CategoriaAfectacion;
    private String tipoAfectacion;
    List<AfectacionAPersona> resultadosAP = new ArrayList<AfectacionAPersona>();
    @Resource(name = "jdbc/sise")
    DataSource dataSource;

    public init() {
        draggableModel = new DefaultMapModel();
    }

    public String getTipoAfectacion() {
        return tipoAfectacion;
    }

    public void setTipoAfectacion(String tipoAfectacion) {
        this.tipoAfectacion = tipoAfectacion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMarker(ActionEvent actionEvent) {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        marker.setDraggable(true);
        draggableModel.addOverlay(marker);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca agregada", "Lat:" + lat + ", Lng:" + lng));
        for (Marker marker1 : draggableModel.getMarkers()) {
            marker1.setDraggable(true);
        }


    }

    public void onMarkerDrag(MarkerDragEvent event) {
        Marker marker = event.getMarker();
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca trasladada", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca Seleccionada", marker.getTitle()));
    }

    public Double getLatitud() throws SQLException {
        Double Latitud;

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        if (getCodDepartamento() == null) {
            Latitud = 13.70;
        } else {
            String id = null;
            if (getCodCaserio() != null) {
                id = getCodCaserio();
            } else if (getCodCanton() != null) {
                id = getCodCanton();
            } else if (getCodMunicipio() != null) {
                id = getCodMunicipio();
            } else if (getCodDepartamento() != null) {
                id = getCodDepartamento();
            }

            PreparedStatement getLatitudes = connection.prepareStatement(
                    "SELECT LATITUDUBIC FROM UBICACION WHERE IDUBIC='" + id + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getLatitudes.executeQuery());
            rowSet.next();
            Latitud = Double.parseDouble(rowSet.getString("LATITUDUBIC"));

            connection.close();
        }
        return Latitud;
    }

    public Double getLongitud() throws SQLException {
        Double Longitud;

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        if (getCodDepartamento() == null) {
            Longitud = 13.70;
        } else {
            String id = null;
            if (getCodCaserio() != null) {
                id = getCodCaserio();
            } else if (getCodCanton() != null) {
                id = getCodCanton();
            } else if (getCodMunicipio() != null) {
                id = getCodMunicipio();
            } else if (getCodDepartamento() != null) {
                id = getCodDepartamento();
            }

            PreparedStatement getLongitudes = connection.prepareStatement(
                    "SELECT LONGITUDUBIC FROM UBICACION WHERE IDUBIC='" + id + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getLongitudes.executeQuery());
            rowSet.next();
            Longitud = Double.parseDouble(rowSet.getString("LONGITUDUBIC"));
            connection.close();
        }
        return Longitud;
    }

    public Integer getZoomUbic() {
        Integer z;
        if (getCodCaserio() != null) {
            z = 20;
        } else if (getCodCanton() != null) {
            z = 15;
        } else if (getCodMunicipio() != null) {
            z = 12;
        } else if (getCodDepartamento() != null) {
            z = 10;
        } else {
            z = 8;
        }
        return z;
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

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public void setDraggableModel(MapModel draggableModel) {
        this.draggableModel = draggableModel;
    }

    public String getIdIncidente() {
        return IdIncidente;
    }

    public void setIdIncidente(String IdIncidente) {
        this.IdIncidente = IdIncidente;
    }

    public String getIdTipoIncidente() {
        return IdTipoIncidente;
    }

    public void setIdTipoIncidente(String IdTipoIncidente) {
        this.IdTipoIncidente = IdTipoIncidente;
        this.IdIncidente = IdTipoIncidente;
    }

    public String getCodCanton() {
        return CodCanton;
    }

    public void setCodCanton(String CodCanton) {
        this.CodCanton = CodCanton;
        this.IdUbicacion = CodCanton;
    }

    public String getCodCaserio() {
        return CodCaserio;
    }

    public void setCodCaserio(String CodCaserio) {
        this.CodCaserio = CodCaserio;
        this.IdUbicacion = CodCaserio;
    }

    public String getIdCatIncidente() {
        return IdCatIncidente;
    }

    public void setIdCatIncidente(String IdCatIncidente) {
        this.IdCatIncidente = IdCatIncidente;
        this.IdIncidente = IdCatIncidente;
    }

    public String getIdUbicacion() {
        return IdUbicacion;
    }

    public void setIdUbicacion(String IdUbicacion) {
        this.IdUbicacion = IdUbicacion;
    }

    public int getTpInstitucion() {
        return TpInstitucion;
    }

    public void setTpInstitucion(int TpInstitucion) {
        this.TpInstitucion = TpInstitucion;
    }

    public String getCodDepartamento() {
        return CodDepartamento;
    }

    public void setCodDepartamento(String CodDepartamento) {
        this.CodDepartamento = CodDepartamento;
        this.IdUbicacion = CodDepartamento;
    }

    public String getCodMunicipio() {
        return CodMunicipio;
    }

    public void setCodMunicipio(String CodMunicipio) {
        this.CodMunicipio = CodMunicipio;
        this.IdUbicacion = CodMunicipio;
    }

    public List<Rol> getRoles() throws SQLException {
        List<Rol> resultados = new ArrayList<Rol>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getRol = connection.prepareStatement(
                    "SELECT IDROL, NOMBROL, DESCROL FROM ROL");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getRol.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Rol(rowSet.getInt("IDROL"),
                        rowSet.getString("NOMBROL"),
                        rowSet.getString("DESCROL")));
            }
            return resultados;
        } finally {
            connection.close();
        }
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

    public List<Estado> getEstadosValidacion() throws SQLException {

        List<Estado> resultados = new ArrayList<Estado>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getEstado = connection.prepareStatement(
                    "SELECT IDESTADO, NOMBESTADO,COLORESTADO FROM ESTADO WHERE IDESTADO NOT IN (1, 8)");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getEstado.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Estado(rowSet.getInt("IDESTADO"),
                        rowSet.getString("NOMBESTADO"),
                        rowSet.getString("COLORESTADO")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Estado> getEstados() throws SQLException {
        List<Estado> resultados = new ArrayList<Estado>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getEstado = connection.prepareStatement(
                    "SELECT IDESTADO, NOMBESTADO,COLORESTADO FROM ESTADO");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getEstado.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Estado(rowSet.getInt("IDESTADO"),
                        rowSet.getString("NOMBESTADO"),
                        rowSet.getString("COLORESTADO")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Estado> getEstadoCierre() throws SQLException {

        List<Estado> resultados = new ArrayList<Estado>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getEstado = connection.prepareStatement(
                    "SELECT IDESTADO, NOMBESTADO,COLORESTADO FROM ESTADO WHERE IDESTADO NOT IN(1,2,3) ORDER BY IDESTADO DESC");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getEstado.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Estado(rowSet.getInt("IDESTADO"),
                        rowSet.getString("NOMBESTADO"),
                        rowSet.getString("COLORESTADO")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Prioridad> getPrioridades() throws SQLException {

        List<Prioridad> resultados = new ArrayList<Prioridad>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getPrioridad = connection.prepareStatement(
                    "SELECT idprior, nombprior,descprior FROM PRIORIDADINCIDENTE");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getPrioridad.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Prioridad(rowSet.getInt("IDPRIOR"),
                        rowSet.getString("NOMBPRIOR"),
                        rowSet.getString("DESCPRIOR")));
            }
            //return rowSet;
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
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodMunicipio + "' order by NOMBUBIC";
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
            String query = "SELECT IDUBIC, NOMBUBIC, LATITUDUBIC, LONGITUDUBIC FROM UBICACION WHERE IDUBIC_PADRE = '" + CodCanton + "' order by NOMBUBIC";
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

    public List<TpIncidente> getTpIncidentes() throws SQLException {

        List<TpIncidente> resultados = new ArrayList<TpIncidente>();

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getTpIncidente = connection.prepareStatement(
                    "SELECT idtpinc, nombtpinc, desctpinc FROM TIPOINCIDENTE WHERE estadotpinc='H'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTpIncidente.executeQuery());

            while (rowSet.next()) {
                resultados.add(new TpIncidente(rowSet.getString("IDTPINC"),
                        rowSet.getString("NOMBTPINC"),
                        rowSet.getString("DESCTPINC")));
            }
            return resultados;
        } finally {
            connection.close();
        }

    }

    public List<TipoIncidente> getTipoIncidente() throws SQLException {
        List<TipoIncidente> resultados = new ArrayList<TipoIncidente>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getTpInstitucion = connection.prepareStatement(
                    "SELECT IDTPINC, NOMBTPINC FROM TIPOINCIDENTE WHERE IDTPINCPADRE LIKE'" + IdCatIncidente + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTpInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new TipoIncidente(rowSet.getString("IDTPINC"),
                        rowSet.getString("NOMBTPINC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Incidente> getIncidentesActuales() throws SQLException {

        List<Incidente> resultados = new ArrayList<Incidente>();
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        LoginBean nB = (LoginBean) session.getAttribute("loginBean");

        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getPrioridad = connection.prepareStatement(
                    "SELECT TI.NOMBTPINC, I.FECHORAINIINC,I.DIRINC, I.PTOREFINC, I.IDESTADO FROM INCIDENTE I JOIN TIPOINCIDENTE TI ON TI.IDTPINC=I.IDTPINC "
                    + "WHERE I.IDEV='" + nB.getIdEvento() + "' AND IDUBIC LIKE '" + IdUbicacion + "%' AND I.IDTPINC LIKE '" + IdIncidente + "%'"
                    + "ORDER BY I.IDPRIOR");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getPrioridad.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Incidente(rowSet.getInt("IDESTADO"),
                        rowSet.getString("NOMBTPINC"),
                        rowSet.getDate("FECHORAINIINC"),
                        rowSet.getString("DIRINC"),
                        rowSet.getString("PTOREFINC")));
            }
            return resultados;
        } finally {
            connection.close();
        }

    }

    public List<TipoIncidente> getCategoriaIncidente() throws SQLException {
        List<TipoIncidente> resultados = new ArrayList<TipoIncidente>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }
        try {
            PreparedStatement getTpInstitucion = connection.prepareStatement(
                    "SELECT IDTPINC, NOMBTPINC FROM TIPOINCIDENTE WHERE IDTPINCPADRE is null");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getTpInstitucion.executeQuery());
            while (rowSet.next()) {
                resultados.add(new TipoIncidente(rowSet.getString("IDTPINC"),
                        rowSet.getString("NOMBTPINC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Evento> getEventos() throws SQLException {
        List<Evento> resultados = new ArrayList<Evento>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getRol = connection.prepareStatement(
                    "SELECT IDEV, NOMBEV FROM EVENTO WHERE ESTADOEV='H'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getRol.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Evento(rowSet.getString("IDEV"),
                        rowSet.getString("NOMBEV")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Afectacion> getCategoriaAfectaciones() throws SQLException {
        List<Afectacion> resultados = new ArrayList<Afectacion>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getAfectacion = connection.prepareStatement(
                    "SELECT IDTPAFEC, NOMBTPAFEC FROM TIPOAFECTACION WHERE IDTPAFEC_PADRE is null");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getAfectacion.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Afectacion(rowSet.getString("IDTPAFEC"),
                        rowSet.getString("NOMBTPAFEC")));
            }
            return resultados;
        } finally {
            connection.close();
        }

    }

    public List<Afectacion> getTipoAfectaciones() throws SQLException {
        List<Afectacion> resultados = new ArrayList<Afectacion>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getAfectacion = connection.prepareStatement(
                    "SELECT IDTPAFEC, NOMBTPAFEC FROM TIPOAFECTACION WHERE IDTPAFEC_PADRE='" + CategoriaAfectacion + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getAfectacion.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Afectacion(rowSet.getString("IDTPAFEC"),
                        rowSet.getString("NOMBTPAFEC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<Afectacion> getCausasAfectaciones() throws SQLException {
        List<Afectacion> resultados = new ArrayList<Afectacion>();
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {
            PreparedStatement getAfectacion = connection.prepareStatement(
                    "SELECT IDTPAFEC, NOMBTPAFEC FROM TIPOAFECTACION WHERE IDTPAFEC_PADRE='" + tipoAfectacion + "'");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getAfectacion.executeQuery());

            while (rowSet.next()) {
                resultados.add(new Afectacion(rowSet.getString("IDTPAFEC"),
                        rowSet.getString("NOMBTPAFEC")));
            }
            return resultados;
        } finally {
            connection.close();
        }
    }

    public List<AfectacionAPersona> getAfectacionesPersonas() throws SQLException {
        //List<AfectacionAPersona> resultados = new ArrayList<AfectacionAPersona>();
        //resultados.removeAll(resultados);

        //resultadosAP.clear();
        
        /*if(!resultadosAP.isEmpty()){
            resultadosAP.clear();
        } */     
        
        if (dataSource == null) {
            throw new SQLException("No se pudo tener acceso a la fuente de datos");
        }
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("No se pudo conectar a la fuente de datos");
        }

        try {

            PreparedStatement getAfectacionPersona = connection.prepareStatement(
                    "select IDAFECPER,IDAFEC,NOMBTPAFEC,NOMBAFECPER,EDADAFECPER,GENEROAFECPER,DISCAFECPER,CENTROASISTAFECPER from AFECTACIONAPERSONA AP JOIN TIPOAFECTACION TA ON AP.IDAFEC=TA.IDTPAFEC");
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(getAfectacionPersona.executeQuery());

            while (rowSet.next()) {
                resultadosAP.add(new AfectacionAPersona(
                        rowSet.getInt("IDAFECPER"),
                        rowSet.getString("IDAFEC"),
                        rowSet.getString("NOMBTPAFEC"),
                        rowSet.getString("NOMBAFECPER"),
                        rowSet.getInt("EDADAFECPER"),
                        rowSet.getString("GENEROAFECPER"),
                        rowSet.getString("DISCAFECPER"),
                        rowSet.getString("CENTROASISTAFECPER")));
            }
            
            //rowSet.close();

            return resultadosAP;
        } finally {
            connection.close();
            
        }
    }

    public String getCategoriaAfectacion() {
        return CategoriaAfectacion;
    }

    public void setCategoriaAfectacion(String CategoriaAfectacion) {
        this.CategoriaAfectacion = CategoriaAfectacion;
    }
}
